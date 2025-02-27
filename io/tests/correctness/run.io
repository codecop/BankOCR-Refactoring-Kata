#!/usr/bin/env io

// patch TestRunner to upstream Io's version
TestRunner run = method(testMap,
    self cases := testMap # Storing a reference to the test map.
    self runtime := Date secondsToRun(
        testMap foreach(testCaseName, testSlotNames,
            # Depending on the Lobby is kind of wacky, but that's
            # all we can do, since Map only supports string keys.
            testCase := Lobby getSlot(testCaseName)
            testSlotNames foreach(name,
                testCase setUp
                exc := try(stopStatus(testCase doString(name)))
                if(exc, error(testCaseName .. " " .. name, exc), success)
                testCase tearDown
            )
        )
    )

    printExceptions
    printSummary
    self exceptions
)

if(System args size > 1,
    # Run specific tests.
    System args slice(1) foreach(name,
        Lobby doFile(System launchPath .. "\\" ..  name)
    )
    System exit(FileCollector run size)
,
    # Run all tests in the current directory.
    System exit(DirectoryCollector run size)
)
