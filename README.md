# PhotoStory_Pinterest
Cookpad Assignment

Submitted by: **Minh Nguyen**

Time spent: 27 hours spent in total

##Before Testing 
Please install Pinterest app on your device. Currently, the OAuthentication through the website of Pinterest is having bugs.

## User Stories
The part of the photo viewer application. Integrating Pinterest to query data from server.

## Techniques
- Support Android 4.0+ but should run on Android 5.0+ to see full animation and nice UI.

- Application mode.

- Mainly focus on UI/UX. I do not use popular component like RecyclerView. I want to expose something weird in UI aspect.

- Process the loading signal of images (including placeholder) - Caching when see big size image.

- I intend to load 10 items per request then load more items. However, I just should complete the assignment in 2 days due to the advice.

- Try to use Lambdas in code.

## More
The application is simple in back-end aspect. However, I had done some similar apps in the past:
- <a href="https://github.com/minhlunso1/InstagramPhotoViewer">Instagram</a>. I used RxAndroid and OkHttp to call API. Instagram has updated the API so the source code does not work anymore but you still can look at it and the .gif file :).

- <a href="https://github.com/minhlunso1/Gr4PhotoStorage">Gr4 Photo Storage</a>. Many techinques was used in app such as: database, uploading process, camera...

## Walkthrough
  ![screenshot](slide.gif)
