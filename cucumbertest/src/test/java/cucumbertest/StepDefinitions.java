package cucumbertest;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.After;
import io.cucumber.java.Before;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.seljup.SeleniumJupiter;
import io.github.bonigarcia.wdm.WebDriverManager;

@ExtendWith(SeleniumJupiter.class)
public class StepDefinitions {
    private ChromeDriver driver;

    @Before()
    public void setUp() {
        WebDriverManager.chromedriver().setup();// .version("100.0.4896.60").setup();
        driver = new ChromeDriver();
        System.out.println("Entered test");
        driver.get("http://localhost:4200");
    }

    @Given("I have items in my cart")
    public void i_have_items_in_my_cart() throws InterruptedException {
        System.out.println("i have items in cart");
        driver.findElement(By.linkText("Products")).click();
        TimeUnit.SECONDS.sleep(1); // give website time to think
        driver.findElement(By.cssSelector(".col-4:nth-child(1) > button")).click();
        driver.findElement(By.cssSelector(".fa-shopping-cart")).click();
        // Write code here that turns the phrase above into concrete actions

    }

    @Given("I've made an order")
    public void i_make_an_order() throws InterruptedException {
        driver.findElement(By.linkText("Products")).click();
        TimeUnit.SECONDS.sleep(1); // give website time to think
        driver.findElement(By.cssSelector(".col-4:nth-child(1) > button")).click();
        driver.findElement(By.cssSelector(".fa-shopping-cart")).click();
        driver.findElement(By.linkText("Make Purchase")).click();
        driver.findElement(By.cssSelector(".confirm")).click();
        TimeUnit.SECONDS.sleep(1); // give website time to think
    }

    @And("I click the \"Make purchase\" button")
    public void i_click_the_make_purchase_button() {
        driver.findElement(By.linkText("Make Purchase")).click();
    }

    @And("my information is right")
    public void right_information() {
        assertEquals("Andreia", driver.findElement(By.id("i1")).getAttribute("value"));
        assertEquals("Sesame", driver.findElement(By.id("i4")).getAttribute("value"));

    }

    @And("my information is wrong")
    public void wrong_information() {
        assertNotEquals("Sacramento", driver.findElement(By.id("i4")).getAttribute("value"));
    }

    @And("I alter my information")
    public void alter_information() {
        driver.findElement(By.cssSelector(".fa-pencil-square-o")).click();
        driver.findElement(By.id("i4")).clear();
        driver.findElement(By.id("i4")).sendKeys("Sacramento");
    }

    @And("I click \"Confirm\"")
    public void i_click_confirm() {
        driver.findElement(By.cssSelector(".confirm")).click();
    }

    @When("I go to my profile")
    public void i_go_to_my_profile() throws InterruptedException {
        driver.findElement(By.linkText("Profile")).click();
        TimeUnit.SECONDS.sleep(1); // give website time to think
    }

    @And("I find my order and click \"track\"")
    public void track_order() throws InterruptedException {
        driver.findElement(By.cssSelector(".fa-search")).click();
        TimeUnit.SECONDS.sleep(5); // give website time to think
    }

    @Then("the order shows in my orders")
    public void order_show_in_profile() throws Exception {
        assertTrue(driver.findElement(By.cssSelector("tr:nth-child(2)")).isDisplayed());
    }

    /*
     * Given I've made an order
     * When I go to my profile
     * And I find my order and click "track"
     * Then I get information about its whereabouts
     */
    @And("my profile information is right")
    public void my_information_is_right() throws Exception {
        assertEquals("Sacramento", driver.findElement(By.id("i4")).getAttribute("value"));
    }

    @Then("I get information about its whereabouts")
    public void get_location() throws Exception {
        // verificar que aparece mapa
        assertTrue(driver.findElement(By.cssSelector("area")).isDisplayed());
        // assertTrue(driver.findElement(By.cssSelector("tr:nth-child(2)")).isDisplayed());
    }

    @After()
    public void closeBrowser() throws InterruptedException {
        driver.quit();
        TimeUnit.SECONDS.sleep(1); // give website time to think
    }
}
