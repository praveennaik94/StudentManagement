
package com.praveen.StudentManagement;

import net.datafaker.Faker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Component
public class StudentSeeder implements CommandLineRunner {

    private static final Logger log =
            LoggerFactory.getLogger(StudentSeeder.class);

    private static final int TOTAL_RECORDS = 10_000_000;
    private static final int BATCH_SIZE = 50_000;

    private static final String INSERT_SQL = """
            INSERT INTO student
            (name, branch, maths_marks, science_marks,
             english_marks, phone_number)
            VALUES (?, ?, ?, ?, ?, ?)
            """;

    private static final String[] BRANCHES = {
            "CSE", "ECE", "EEE", "Mechanical",
            "Civil", "IT", "AI&DS"
    };

    private final JdbcTemplate jdbcTemplate;

    @Value("${app.seed.enabled:false}")
    private boolean seedEnabled;

    public StudentSeeder(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void run(String... args) {

        if (!seedEnabled) {
            log.info("Student seeding is disabled.");
            return;
        }

        Long count = jdbcTemplate.queryForObject(
                "SELECT COUNT(*) FROM student",
                Long.class
        );

        if (count != null && count > 0) {
            log.info(
                    "Student table already contains {} records. Skipping seeding.",
                    count
            );
            return;
        }

        Faker faker = new Faker();
        ThreadLocalRandom random = ThreadLocalRandom.current();

        log.info("Starting student seeding: {} records", TOTAL_RECORDS);

        long startTime = System.currentTimeMillis();

        for (int start = 0; start < TOTAL_RECORDS;
             start += BATCH_SIZE) {

            int currentBatchSize = Math.min(
                    BATCH_SIZE, TOTAL_RECORDS - start
            );

            List<Object[]> batch =
                    new ArrayList<>(currentBatchSize);

            for (int i = 0; i < currentBatchSize; i++) {

                String name = faker.name().fullName();

                String branch = BRANCHES[
                        random.nextInt(BRANCHES.length)
                        ];

                int mathsMarks = random.nextInt(0, 101);
                int scienceMarks = random.nextInt(0, 101);
                int englishMarks = random.nextInt(0, 101);

                // Generate a synthetic 10-digit phone number
                String phoneNumber = "9" +
                        String.format(
                                "%09d",
                                random.nextLong(1_000_000_000L)
                        );

                batch.add(new Object[]{
                        name,
                        branch,
                        mathsMarks,
                        scienceMarks,
                        englishMarks,
                        phoneNumber
                });
            }

            jdbcTemplate.batchUpdate(INSERT_SQL, batch);

            int inserted = start + currentBatchSize;

            log.info(
                    "Inserted {} / {} students",
                    inserted, TOTAL_RECORDS
            );
        }

        long elapsedSeconds =
                (System.currentTimeMillis() - startTime) / 1000;

        log.info(
                "Seeding completed. Total records: {}. Time: {} seconds",
                TOTAL_RECORDS, elapsedSeconds
        );
    }
}
