# BigNumber-Java

A library for handle large Integer numbers with beauty print.

## Setup

Step 1. Add it in your root build.gradle at the end of repositories:
```
allprojects {
    repositories {
	maven { url 'https://jitpack.io' }
    }
}
```
Step 2. Add the dependency:

```
dependencies {
	implementation 'com.github.tareqya:BigIntegerNumber-Java:1.0.0'
}
```

## Usage

###### StepProgress Constructor:
```java
        BigNumber b1 = new BigNumber("1_000_000_000_000_000");
        BigNumber b2 = new BigNumber("99_999_000_009_789_000");
        BigNumber res = b1.add(b2);
        System.out.println(res);
```
