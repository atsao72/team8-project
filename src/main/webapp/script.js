// Copyright 2020 Google LLC
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     https://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

/**
 * function that retrieves the users's google id_token when they sign in
 */
function onSignIn(googleUser) {
    // get user's googleId token which we will pass to the backend
    const id_token = googleUser.getAuthResponse().id_token;
    
    const params = new URLSearchParams;
    params.append('ID_token', id_token);

    fetch('/sign-in', {method: 'POST', body: params})
    .then(response => {
       redirect();
    });
}

/*
 *function to handle redirecting the user to their homepage after a successfull sign in
 */
function redirect(){
    fetch('/redirect', {method: 'POST'})
        .then(response => {
            //window.location.replace(response.url);
            window.location.href = response.url;
    });
}