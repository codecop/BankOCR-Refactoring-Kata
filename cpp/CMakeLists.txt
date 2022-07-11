cmake_minimum_required(VERSION 3.16)
project(bank-ocr-refactoring-kata)

enable_testing()

include(FetchContent)
FetchContent_Declare(
        googletest
        GIT_REPOSITORY https://github.com/google/googletest.git
        GIT_TAG        release-1.10.0
        GIT_SHALLOW    1
)
FetchContent_MakeAvailable(googletest)
include(GoogleTest)

set(CMAKE_CXX_STANDARD 11)

set(SOURCES Ocr.cpp Ocr.h)

set(TEST_NAME OcrTest)
set(TEST_SOURCES main.cpp ocr_gtest.cpp)

add_executable(${TEST_NAME})
target_sources(${TEST_NAME} PRIVATE ${TEST_SOURCES} ${SOURCES})
target_link_libraries(${TEST_NAME} gtest gmock)
gtest_discover_tests(${TEST_NAME})
