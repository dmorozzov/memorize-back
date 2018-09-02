import axios from "axios";
import {ACCESS_TOKEN, API_BASE_URL} from "./constants";

const headers = {
    'Content-Type': 'application/json',
};

const defaultSuccessHandler = (result) => {console.log("No specific success handler: " + result)};
const defaultFailureHandler = (err) => {console.error("No specific failure handler. Error: " + err)};

const doPostRequest = function (url, payload, success = defaultSuccessHandler, failure = defaultFailureHandler) {
    let token = localStorage.getItem(ACCESS_TOKEN);
    let currHeaders = Object.assign({}, headers);
    if (token) {
        currHeaders['Authorization'] = 'Bearer ' + token;
    }
    return axios({
        method: 'POST',
        headers: currHeaders,
        url: API_BASE_URL + url,
        data: payload
    })
        .then(result => success(result))
        .catch(err => failure(err));
};

const doGetRequest = function (url, success, failure) {
    let token = localStorage.getItem(ACCESS_TOKEN);
    let currHeaders = Object.assign({}, headers);
    if (token) {
        currHeaders['Authorization'] = 'Bearer ' + token;
    }
    return axios({
        method: 'GET',
        headers: currHeaders,
        url: API_BASE_URL + url
    })
        .then(result => success(result))
        .catch(err => failure(err));
};

export function login(loginRequest, success, error) {
    debugger;
    return doPostRequest('/auth/signin', loginRequest, success, error);
}

export function getCurrentUser() {
    debugger;
    if (!localStorage.getItem(ACCESS_TOKEN)) {
        return Promise.reject("No access token set.");
    }

    return doGetRequest('/user/me');
}

export default class Resourses {
  constructor() {}

  static fetchWords(params) {
    const self = this;
    return axios({
      method: "post",
      url: "/api/user/words",
      data: params
    });
    // let promise = new Promise((resolve, reject) => {
    //   setTimeout(() => {
    //     resolve({
    //       data: self.createFakeData(count, 20)
    //     });
    //   }, 3000);
    // });
    // return promise;
  }

  static saveWord(request) {
    return axios({
      method: "post",
      url: "api/word/save",
      data: request
    });
  }
}
