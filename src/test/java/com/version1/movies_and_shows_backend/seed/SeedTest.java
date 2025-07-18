package com.version1.movies_and_shows_backend.seed;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;

import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoSpyBean;

import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.verify;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional
class SeedTest {

    @MockitoSpyBean
    private Seed seed;

    @Test
    void getCSVPath_returnsCorrectPath() {
        Path expected = Path.of("data/test").toAbsolutePath().normalize();
        Path actual = seed.getCSVPath("test");
        assertEquals(expected, actual.normalize());
    }

    @Test
    void seedDataWorkflowTest() {
        // Arrange
        List<String> siteNames = List.of("test", "test-2");

        // Act
        seed.seedData(siteNames);

        // Assert: Verify internal method calls
        verify(seed).seedSites(siteNames);
        verify(seed).seedMedia(anyList());
        verify(seed).seedCredits(anyList(), anyList());
    }
}
