import { makeHTTPRequest } from "../services/Service";

// ACTIONS TO LOGIN

export const FETCH_AUTH_SUCCESS = "FETCH_AUTH_SUCCESS";
export const FETCH_AUTH_FAILURE = "FETCH_AUTH_FAILURE";

export function authToAPI(url, request, dispatch) {
  const success = (res) => dispatch(fetchAuthSuccess(res));
  const failure = (err) => dispatch(fetchAuthFailure(err));
  makeHTTPRequest(url, request, success, failure);
}

function fetchAuthSuccess(res) {
  return {
    type: FETCH_AUTH_SUCCESS,
    payload: {
      data: { ...res },
    },
  };
}

function fetchAuthFailure(err) {
  return {
    type: FETCH_AUTH_FAILURE,
    payload: {
      error: err,
    },
  };
}

// ACTIONS TO COLLECTIONS

export const FETCH_COLLECTIONS_SUCCESS = "FETCH_COLLECTIONS_SUCCESS";
export const FETCH_COLLECTIONS_FAILURE = "FETCH_COLLECTIONS_FAILURE";

export function fetchCollections(url, request, dispatch) {
  const success = (res) => dispatch(fetchCollectionsSuccess(res));
  const failure = (err) => dispatch(fetchCollectionsFailure(err.message));
  makeHTTPRequest(url, request, success, failure);
}

function fetchCollectionsSuccess(res) {
  return {
    type: FETCH_COLLECTIONS_SUCCESS,
    payload: {
      data: { ...res },
    },
  };
}

function fetchCollectionsFailure(message) {
  return {
    type: FETCH_COLLECTIONS_FAILURE,
    payload: {
      error: message,
    },
  };
}

// ACTIONS TO DETAILS OF AN OBJECT FROM COLLETIONS

export const FETCH_DETAILS_STARTED = "FETCH_DETAILS_STARTED";
export const FETCH_DETAILS_SUCCESS = "FETCH_DETAILS_SUCCESS";
export const FETCH_DETAILS_FAILURE = "FETCH_DETAILS_FAILURE";
export const DELETE_DETAILS = "DELETE_DETAILS";

export function fetchDetails(url, request, dispatch) {
  const success = (res) => dispatch(fetchDetailsSuccess(res));
  const failure = (err) => dispatch(fetchDetailsFailure(err.message));
  makeHTTPRequest(url, request, success, failure);
}

export function fetchDetailsStarted(id) {
  return {
    type: FETCH_DETAILS_STARTED,
    payload: {
      userid: id,
    },
  };
}

export function fetchDetailsSuccess(details) {
  return {
    type: FETCH_DETAILS_SUCCESS,
    payload: {
      data: [...details],
    },
  };
}

export function fetchDetailsFailure(message) {
  return {
    type: FETCH_DETAILS_FAILURE,
    payload: {
      error: message,
    },
  };
}

export function deleteDetails() {
  return {
    type: DELETE_DETAILS,
  };
}

// ACTIONS TO NAVIGATE

export const NAV_TO_FORM = "NAV_TO_FORM";
export const NAV_TO_TABLE = "NAV_TO_TABLE";
export const NAV_TO_DETAILS = "NAV_TO_DETAILS";
export const NAV_TO_EDITDETAILS = "NAV_TO_EDITDETAILS";

export function navToForm(dispatch) {
  dispatch(formTrue());
}

export function navToTable(dispatch) {
  dispatch(tableTrue());
}

export function navToDetails(dispatch) {
  dispatch(detailsTrue());
}

export function navToEditDetails(dispatch) {
  dispatch(editDetailsTrue());
}

export function formTrue() {
  return {
    type: NAV_TO_FORM,
  };
}

export function tableTrue() {
  return {
    type: NAV_TO_TABLE,
  };
}

export function detailsTrue() {
  return {
    type: NAV_TO_DETAILS,
  };
}

export function editDetailsTrue() {
  return {
    type: NAV_TO_EDITDETAILS,
  };
}
