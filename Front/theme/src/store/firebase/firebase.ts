import firebase from "firebase/compat/app";
import "firebase/compat/auth";
import "firebase/compat/storage";
import { initializeApp } from "firebase/app";
import { getAnalytics } from "firebase/analytics";

const firebaseConfig = {
  apiKey: process.env.VUE_APP_FB_API_KEY,
  authDomain: process.env.VUE_APP_FB_AUTH_DOMAIN,
  projectId: process.env.VUE_APP_FB_PROJECT_ID,
  storageBucket: process.env.VUE_APP_FB_STORAGEBUCKET,
  messagingSenderId: process.env.VUE_APP_FB_MESSAGING_SENDER_ID,
  appId: process.env.VUE_APP_FB_APP_ID,
  measurementId: process.env.VUE_APP_FB_MEASUREMENT_ID
};

// Initialize Firebase
// const app = initializeApp(firebaseConfig);
// const analytics = getAnalytics(app);

const app =firebase.initializeApp(firebaseConfig);

const auth_obj = firebase.auth();
const storage_obj = firebase.storage();

const articleStorageRef = storage_obj.ref("/article");
const profileStorageRef = storage_obj.ref("/profile");

const article = (_imageName:string,_image:File) => {
  const articleImageRef = articleStorageRef.child(_imageName)
  articleImageRef.put(_image)
}



// export default firebase;
// export const auth = auth_obj;
// export const storage = storage_obj;
// export const articleImageUpload = (_imageName:string,_image:File) => { article(_imageName,_image) };
// export const  = storage_obj;

