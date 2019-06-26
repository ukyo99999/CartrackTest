package app.appsamurai.cartrack

import androidx.test.core.app.ApplicationProvider
import app.appsamurai.cartrack.util.sql.DbAccess
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

/**
 * Created by Ukyo on 2019-06-20.
 *
 */
@RunWith(RobolectricTestRunner::class)
class LoginTest {
    lateinit var db: DbAccess

    @Before
    fun setup() {
        db = DbAccess(ApplicationProvider.getApplicationContext())
    }

    @Test
    fun `Verify user will be able to login with a valid username and valid password`() {
        db.addUser("Chris", "abc123", "ukyo99999@gmail.com")
        val input = "ukyo99999@gmail.com"

        val password = db.getPassword(input)

        Assert.assertTrue(db.isEmailExist(input)) //if email exist return TRUE
        Assert.assertEquals("abc123", password)
    }

    @After
    fun tearDown() {
        db.closeDB()
    }

}