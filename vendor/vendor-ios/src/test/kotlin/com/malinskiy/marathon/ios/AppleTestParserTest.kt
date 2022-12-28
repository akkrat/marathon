package com.malinskiy.marathon.ios

import com.malinskiy.marathon.config.vendor.VendorConfiguration
import com.malinskiy.marathon.config.vendor.ios.AppleTestBundleConfiguration
import com.malinskiy.marathon.config.vendor.ios.LifecycleAction
import com.malinskiy.marathon.config.vendor.ios.LifecycleConfiguration
import com.malinskiy.marathon.config.vendor.ios.RsyncConfiguration
import com.malinskiy.marathon.config.vendor.ios.SshAuthentication
import com.malinskiy.marathon.config.vendor.ios.SshConfiguration
import kotlinx.coroutines.runBlocking
import org.amshove.kluent.shouldContainSame
import org.junit.jupiter.api.Test
import java.io.File
import com.malinskiy.marathon.test.Test as MarathonTest

class AppleTestParserTest {
    private val sourceRoot =
        File(javaClass.classLoader.getResource("sample-xcworkspace/sample-appUITests").file)
    private val derivedDataDir =
        File(javaClass.classLoader.getResource("sample-xcworkspace/derived-data").file)
    private val xctestrunPath =
        File(javaClass.classLoader.getResource("sample-xcworkspace/derived-data/Build/Products/UITesting_iphonesimulator11.2-x86_64.xctestrun").file)
    private val vendorConfiguration = VendorConfiguration.IOSConfiguration(
        bundle = AppleTestBundleConfiguration(
            derivedDataDir = derivedDataDir,
            testApplication = xctestrunPath,
        ),
        ssh = SshConfiguration(
            authentication = SshAuthentication.PublicKeyAuthentication(
                username = "testuser",
                key = File("/home/fakekey"),
            ),
            knownHostsPath = null,
            debug = false,
        ),
        rsync = RsyncConfiguration(remotePath = "/remote/rsync"),
        sourceRoot = sourceRoot,
        lifecycleConfiguration = LifecycleConfiguration(
            onPrepare = setOf(LifecycleAction.ERASE)
        ),
    )
    private val parser = AppleTestParser(vendorConfiguration, AppleTestBundleIdentifier())

    @Test
    fun `should return accurate list of tests`() {
        val extractedTests = runBlocking {
            parser.extract()
        }

        extractedTests shouldContainSame listOf(
            MarathonTest("sample-appUITests", "StoryboardTests", "testButton", emptyList()),
            MarathonTest("sample-appUITests", "StoryboardTests", "testLabel", emptyList()),
            MarathonTest("sample-appUITests", "MoreTests", "testPresentModal", emptyList()),
            MarathonTest("sample-appUITests", "CrashingTests", "testButton", emptyList()),
            MarathonTest("sample-appUITests", "FailingTests", "testAlwaysFailing", emptyList()),
            MarathonTest("sample-appUITests", "FlakyTests", "testTextFlaky", emptyList()),
            MarathonTest("sample-appUITests", "FlakyTests", "testTextFlaky1", emptyList()),
            MarathonTest("sample-appUITests", "FlakyTests", "testTextFlaky2", emptyList()),
            MarathonTest("sample-appUITests", "FlakyTests", "testTextFlaky3", emptyList()),
            MarathonTest("sample-appUITests", "FlakyTests", "testTextFlaky4", emptyList()),
            MarathonTest("sample-appUITests", "FlakyTests", "testTextFlaky5", emptyList()),
            MarathonTest("sample-appUITests", "FlakyTests", "testTextFlaky6", emptyList()),
            MarathonTest("sample-appUITests", "FlakyTests", "testTextFlaky7", emptyList()),
            MarathonTest("sample-appUITests", "FlakyTests", "testTextFlaky8", emptyList()),
            MarathonTest("sample-appUITests", "SlowTests", "testTextSlow", emptyList()),
            MarathonTest("sample-appUITests", "SlowTests", "testTextSlow1", emptyList()),
            MarathonTest("sample-appUITests", "SlowTests", "testTextSlow2", emptyList()),
            MarathonTest("sample-appUITests", "SlowTests", "testTextSlow3", emptyList()),
            MarathonTest("sample-appUITests", "SlowTests", "testTextSlow4", emptyList())
        )
    }
}
