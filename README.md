# AndroidUtils
Lots of Android utils every project should have

## Usage

Add the dependency

``` bash
dependencies {
            ...
	        compile 'com.github.shahar2k5:androidutils:1.0.0'
	}

```
## API

### » DateUtils

+ Convert Date to string with long format -> formatDateLong
+ Check if the date is less than 7 days from now -> isLessThanOneWeek
+ Check if the specific date is today -> isToday
+ Is the specific date yesterday -> isYesterday
+ Check for a leap year -> thing
+ return date string from milliseconds -> millisecondsToString
+ create time in milliseconds from a formatted string -> stringToMilliseconds
+ create a date from formatted string -> stringToDate
+ Create a formatted date -> dateToString
+ Convert date object to milliseconds -> dateToMilliseconds
+ Convert milliseconds to date object -> millisecondsToDate
+ Convert milliseconds to specific time unit -> thing
+ Convert milliseconds to specific time unit -> millisecondsToTimeUnit
+ Get current time in milliseconds -> getCurrentTimeMillis
+ Get current time in formatted string -> getCurrentTimeString
+ Get current time in date object -> getCurrentTimeDate
+ Calculate time interval from now -> getTimeIntervalFromNow
+ Calculate time interval between 2 dates -> getTimeIntervalBetweenDates

### » DialogUtils

+ Show an Alert Dialog with one button -> showOneButtonsDialog
+ Show an Alert Dialog with two buttons -> showTwoButtonsDialog
+ Displays a dialog box with an OK button -> showOkDialog
+ Creates a dialog box with an OK button -> createOkDialog
+ Dismiss dialog safely -> dismissDialogSafely

### » EncryptionUtils

+ Create MD5 String from string -> getMD5String
+ Create MD5 String with salt -> dismissDialogSafely
+ Create MD5 String from byte array -> dismissDialogSafely
+ Create MD5 String from byte array with salt -> dismissDialogSafely
+ Encrypt byte array -> encryptMD5
+ Create a MD5 String from file path -> getMD5File
+ Create a MD5 String from file -> getMD5File
+ Get SHA1 from String -> getSHA
+ Get SHA1 from byte array -> getSHA
+ Encrypt SHA1 -> encryptSHA
+ Convert bytes to Hex -> bytesToHex

### » ImageUtils

+ Create a Bitmap from URI -> getBitmapFromUri
+ Get file system path from a URI -> getRealPathFromURI
+ Normalize a bitmap to 640px -> normalize
+ Normalize a bitmap to specific size -> normalize
+ Get image rotated by degree from metedata -> getImageRotatedByMetadata
+ Create a fullscreen image and punch a hole inside -> punchARoundedHoleInABitmap

### » IntentUtils

+ Makes a call -> callNumber
+ Dial a number in the phone's keypad -> dialNumber
+ Open compose email activity -> openComposeEmailActivity
+ Open SMS activity to send SMS -> openSendSmsActivity
+ Create a WhatsApp share intent -> createWhatsAppShareIntent
+ Starts an Activity if it is exists -> startIntentIfPossible
+ Check if there is a valid Activity for the intent -> isActivityAvailableForIntent
+ Open the playstore page for the current app -> openPlayStoreAppPage
+ Check if a service is running -> isRunningService\

### » IOUtils

+ Save a Bitmap to a local file -> saveBitmapToFile
+ Delete a selected directory recursively -> deleteDir
+ Create a directory if needed -> createParentDirIfNotExists

### » JavaUtils

+ Get the index of an object -> indexOf
+ Create a Integer arrayList from String arrayList -> convertStringArrayToIntegerArray
+ Check if object is null and throw -> checkNotNull

### » KeyboardUtils

+ Hide soft keyboard -> hideSoftKeyboard
+ Show soft keyboard -> showSoftKeyboard
+ Toggle soft keyboard state -> toggleKeyboradState

### » NetworkUtils

+ Check if there is an active network -> isNetworkAvailable
+ Get the network type -> getNetworkType
+ Toggle soft keyboard state -> '''toggleKeyboradState'''


---

## [License](LICENSE)