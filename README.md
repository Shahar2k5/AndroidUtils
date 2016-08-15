# AndroidUtils
Lots of Android utils every project should have

## Usage

Add jcenter to your project's gradle file

```
allprojects {
    repositories {
        jcenter()
    }
}
```


#### Gradle


```
compile 'com.github.shahar2k5:androidutils:1.0.1'
```


#### Maven
```
<dependency>
  <groupId>com.github.shahar2k5</groupId>
  <artifactId>androidutils</artifactId>
  <version>1.0.1</version>
  <type>pom</type>
</dependency>
```

## API

#### » [DateUtils](androidutils/src/main/java/com/bytesizebit/androidutils/DateUtils.java)

+ Convert Date to string with long format -> `formatDateLong`
+ Check if the date is less than 7 days from now -> `isLessThanOneWeek`
+ Check if the specific date is today -> `isToday`
+ Is the specific date yesterday -> `isYesterday`
+ Check for a leap year -> `isLeapYear`
+ return date string from milliseconds -> `millisecondsToString`
+ create time in milliseconds from a formatted string -> `stringToMilliseconds`
+ create a date from formatted string -> `stringToDate`
+ Create a formatted date -> `dateToString`
+ Convert date object to milliseconds -> `dateToMilliseconds`
+ Convert milliseconds to date object -> `millisecondsToDate`
+ Convert milliseconds to specific time unit -> `millisecondsToTimeUnit`
+ Get current time in milliseconds -> `getCurrentTimeMillis`
+ Get current time in formatted string -> `getCurrentTimeString`
+ Get current time in date object -> `getCurrentTimeDate`
+ Calculate time interval from now -> `getTimeIntervalFromNow`
+ Calculate time interval between 2 dates -> `getTimeIntervalBetweenDates`

#### » [DialogUtils](androidutils/src/main/java/com/bytesizebit/androidutils/DialogUtils.java)

+ Show an Alert Dialog with one button -> `showOneButtonsDialog`
+ Show an Alert Dialog with two buttons -> `showTwoButtonsDialog`
+ Displays a dialog box with an OK button -> `showOkDialog`
+ Creates a dialog box with an OK button -> `createOkDialog`
+ Dismiss dialog safely -> `dismissDialogSafely`

#### » [EncryptionUtils](androidutils/src/main/java/com/bytesizebit/androidutils/EncryptionUtils.java)

+ Create MD5 String from string -> `getMD5String`
+ Create MD5 String with salt -> `dismissDialogSafely`
+ Create MD5 String from byte array -> `dismissDialogSafely`
+ Create MD5 String from byte array with salt -> `dismissDialogSafely`
+ Encrypt byte array -> `encryptMD5`
+ Create a MD5 String from file path -> `getMD5File`
+ Create a MD5 String from file -> `getMD5File`
+ Get SHA1 from String -> `getSHA`
+ Get SHA1 from byte array -> `getSHA`
+ Encrypt SHA1 -> `encryptSHA`
+ Convert bytes to Hex -> `bytesToHex`

#### » [ImageUtils](androidutils/src/main/java/com/bytesizebit/androidutils/ImageUtils.java)

+ Create a Bitmap from URI -> `getBitmapFromUri`
+ Get file system path from a URI -> `getRealPathFromURI`
+ Normalize a bitmap to 640px -> `normalize`
+ Normalize a bitmap to specific size -> `normalize`
+ Get image rotated by degree from metedata -> `getImageRotatedByMetadata`
+ Create a fullscreen image and punch a hole inside -> `punchARoundedHoleInABitmap`

#### » [IntentUtils](androidutils/src/main/java/com/bytesizebit/androidutils/IntentUtils.java)

+ Makes a call -> `callNumber
+ Dial a number in the phone's keypad -> `dialNumber`
+ Open compose email activity -> `openComposeEmailActivity`
+ Open SMS activity to send SMS -> `openSendSmsActivity`
+ Create a WhatsApp share intent -> `createWhatsAppShareIntent`
+ Starts an Activity if it is exists -> `startIntentIfPossible`
+ Check if there is a valid Activity for the intent -> `isActivityAvailableForIntent`
+ Open the playstore page for the current app -> `openPlayStoreAppPage`
+ Check if a service is running -> `isRunningService`

#### » [IOUtils](androidutils/src/main/java/com/bytesizebit/androidutils/IOUtils.java)

+ Save a Bitmap to a local file -> `saveBitmapToFile`
+ Delete a selected directory recursively -> `deleteDir`
+ Create a directory if needed -> `createParentDirIfNotExists`

#### » [JavaUtils](androidutils/src/main/java/com/bytesizebit/androidutils/JavaUtils.java)

+ Get the index of an object -> `indexOf`
+ Create a Integer arrayList from String arrayList -> `convertStringArrayToIntegerArray`
+ Check if object is null and throw -> `checkNotNull`

#### » [KeyboardUtils](androidutils/src/main/java/com/bytesizebit/androidutils/KeyboardUtils.java)

+ Hide soft keyboard -> `hideSoftKeyboard`
+ Show soft keyboard -> `showSoftKeyboard`
+ Toggle soft keyboard state -> `toggleKeyboradState`

#### » [NetworkUtils](androidutils/src/main/java/com/bytesizebit/androidutils/DateUtils.java)

+ Check if there is an active network -> `isNetworkAvailable`
+ Get the network type -> `getNetworkType`
+ Toggle soft keyboard state -> `toggleKeyboradState`

#### » [PermissionUtils](androidutils/src/main/java/com/bytesizebit/androidutils/PermissionUtils.java)

+ Open the app settings to enable permissions -> `openPermissionsSettings`
+ Check if user denied permissions with the flag NEVER ASK AGAIN -> `checkDeniedPermissionsNeverAskAgain`

#### » [ResourcesUtils](androidutils/src/main/java/com/bytesizebit/androidutils/ResourcesUtils.java)

+ Load resource id by name -> `getResIdFromString`

#### » [ScreenUtils](androidutils/src/main/java/com/bytesizebit/androidutils/ScreenUtils.java)

+ Get screen width -> `getScreenWidthInPx`
+ Get screen height -> `getScreenHeightInPx`
+ ScreenSize -> `getScreenSize`
+ Set transparent status bar -> `setTransparentStatusBar`
+ Get status bar height -> `getStatusBarHeight`
+ Check if we have status bar -> `hasStatusBar`
+ Get the action bar height -> `getActionBarHeight`
+ Take a screenshot with the status bar -> `takeScreenShotWithStatusBar`
+ Take a screenshot without the status bar -> `takeScreenShoteWithoutStatusBar`
+ Check if the screen is locked -> `isScreenLocked`

#### » [SizeUtils](androidutils/src/main/java/com/bytesizebit/androidutils/SizeUtils.java)

+ Convert DP to PX -> `dpToPx`
+ Convert PX to DP -> `pxToDp`
+ Convert SP to PX -> `spToPx`
+ Convert PX to SP -> `pxToSp`

#### » [StringUtils](androidutils/src/main/java/com/bytesizebit/androidutils/StringUtils.java)

+ Checks if a String is whitespace, empty ("") or null. -> `isBlank`
+ Checks if a String is not empty (""), not null and not whitespace only. -> `isNotBlank`
+ Check if a string is empty -> `isEmpty`
+ Check if a string is NOT empty -> `isNotEmpty`
+ Capital only first letter -> `capitalizeFirstLetter`
+ Create a String from stacktrace -> `exceptionStackTraceToString`
+ Get initials from a string -> `getInitialsFromString`

#### » [ThreadUtils](androidutils/src/main/java/com/bytesizebit/androidutils/ThreadUtils.java)

+ Makes the thread sleep for some time -> `threadSleep`
+ Check if running on main thread -> `checkUiThread`

#### » [ValidationUtils](androidutils/src/main/java/com/bytesizebit/androidutils/ValidationUtils.java)

+ Check if an email is valid -> `isValidEmail`
+ Check if an IP Address is valid -> `isValidIPAddress`
+ Check if a url is valid -> `isValidUrl`

#### » [VersionUtils](androidutils/src/main/java/com/bytesizebit/androidutils/VersionUtils.java)

+ `isNougatOrHigher`
+ `isMarshmallowOrHigher`
+ `isLollipopOrHigher`
+ `isKitKatOrHigher`
+ `isJellyBeanOrHigher`

#### » [ViewUtils](androidutils/src/main/java/com/bytesizebit/androidutils/ViewUtils.java)

+ Set visibility to sibling views -> `setSiblingsVisibility`
+ Scroll listview to bottom -> `listViewScrollToBottom`
+ Create an underline text clickable -> `makeUnderlinedTextClickable`

---

## [License](LICENSE)
