import firebase from "firebase/compat/app";
import "firebase/compat/auth";
import "firebase/compat/storage";
import { getStorage, ref, getDownloadURL } from "firebase/storage";

const firebaseConfig = {
  apiKey: process.env.VUE_APP_FB_API_KEY,
  authDomain: process.env.VUE_APP_FB_AUTH_DOMAIN,
  projectId: process.env.VUE_APP_FB_PROJECT_ID,
  storageBucket: process.env.VUE_APP_FB_STORAGEBUCKET,
  messagingSenderId: process.env.VUE_APP_FB_MESSAGING_SENDER_ID,
  appId: process.env.VUE_APP_FB_APP_ID,
  measurementId: process.env.VUE_APP_FB_MEASUREMENT_ID
};

const app =firebase.initializeApp(firebaseConfig);

const auth_obj = firebase.auth();
const storage_obj = firebase.storage();

const articleStorageRef = storage_obj.ref("/article");
const profileStorageRef = storage_obj.ref("/profile");

const articleImageUpload = (_imageName:string,_image:File) => {
  const articleImageRef = articleStorageRef.child(_imageName)
  articleImageRef.put(_image)
  const url = `https://firebasestorage.googleapis.com/v0/b/theme-b8677.appspot.com/o/article%2F${_imageName}?alt=media`
  return url
}
const profileImageUpload = (_imageName:string,_image:File) =>{
  const profileImageRef = profileStorageRef.child(_imageName)
  profileImageRef.put(_image)
  const url = `https://firebasestorage.googleapis.com/v0/b/theme-b8677.appspot.com/o/profile%2F${_imageName}?alt=media`
  return url
}

// 조회도 가능
const storage = getStorage();
const getImageUrl = (_type:string,_imageName:string)=>{
  getDownloadURL(ref(storage,`${_type}/${_imageName}`))
    .then((url) => {
      console.log(url)
    })
    .catch((error) => {
      // Handle any errors
    });
  }

export {articleImageUpload,profileImageUpload,getImageUrl}