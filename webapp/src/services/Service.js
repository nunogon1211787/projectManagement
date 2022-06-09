export const URL_API = "http://localhost:8080";

export function makeHTTPRequest(url, request, success, failure) {
  fetch(url, request)
    .then((res) => {
      if (res.status >= 200 && res.status < 300) {
        return Promise.resolve(res);
      }
      return Promise.reject(res);
    })
    .then((res) => res.json())
    .then((res) => success(res))
    .catch((err) => {
      err.json().then((err) => {
        failure(err.errorMessage);
      });
    });
}
