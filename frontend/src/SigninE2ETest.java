import com.microsoft.playwright.*;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.util.UUID;

public class SigninE2ETest {
    static Playwright playwright;
    static Browser browser;
    BrowserContext context;
    Page page;

    @BeforeAll
    static void setupAll() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(true));
    }

    @AfterAll
    static void teardownAll() {
        browser.close();
        playwright.close();
    }

    @BeforeEach
    void setup() {
        context = browser.newContext();
        page = context.newPage();
    }

    @AfterEach
    void teardown() {
        context.close();
    }

    @Test
    void testSignupRedirectsToSignin() {
        String unique = UUID.randomUUID().toString().substring(0, 8);
        String email = "test" + unique + "@test.com";
        String username = "testuser" + unique;
        String password = "testpass1234";
        page.navigate("http://localhost:3000/signup");
        page.fill("input[name=\"email\"]", email);
        page.fill("input[name=\"username\"]", username);
        page.fill("input[name=\"password\"]", password);
        page.click("button[type=\"submit\"]");
        // Wait for redirect to signin
        page.waitForURL("http://localhost:3000/signin");
        assertEquals("http://localhost:3000/signin", page.url(), "Should redirect to signin after signup");
    }

    @Test
    void testSignupWithExistingUsernameShowsError() {
        String unique = UUID.randomUUID().toString().substring(0, 8);
        String email = "unique" + unique + "@test.com";
        String password = "testpass1234";
        page.navigate("http://localhost:3000/signup");
        page.fill("input[name=\"email\"]", email);
        page.fill("input[name=\"username\"]", "admin");
        page.fill("input[name=\"password\"]", password);
        page.click("button[type=\"submit\"]");
        page.waitForSelector("text=Username already exists");
        assertTrue(page.locator("input[name=\"username\"]").evaluate("el => el.classList.contains('border-red-500')"));
    }

    @Test
    void testSignupWithExistingEmailShowsError() {
        String unique = UUID.randomUUID().toString().substring(0, 8);
        String username = "uniqueuser" + unique;
        String password = "testpass1234";
        page.navigate("http://localhost:3000/signup");
        page.fill("input[name=\"email\"]", "admin@test.nl");
        page.fill("input[name=\"username\"]", username);
        page.fill("input[name=\"password\"]", password);
        page.click("button[type=\"submit\"]");
        page.waitForSelector("text=Email already exists");
        assertTrue(page.locator("input[name=\"email\"]").evaluate("el => el.classList.contains('border-red-500')"));
    }

    @Test
    void testAdminSignin() {
        page.navigate("http://localhost:3000/signin");
        page.fill("input[name=\"username\"]", "admin");
        page.fill("input[name=\"password\"]", "admin1234");
        page.click("button[type=\"submit\"]");
        page.waitForTimeout(1000);
        String token = page.evaluate("() => localStorage.getItem('token')");
        assertNotNull(token, "JWT token should be present in localStorage after successful signin");
    }

    @Test
    void testSigninWithWrongPasswordShowsError() {
        page.navigate("http://localhost:3000/signin");
        page.fill("input[name=\"username\"]", "admin");
        page.fill("input[name=\"password\"]", "wrongpass");
        page.click("button[type=\"submit\"]");
        page.waitForSelector("text=Invalid credentials");
        assertTrue(page.content().contains("Invalid credentials"));
    }
} 