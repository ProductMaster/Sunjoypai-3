/*
    Copyright 2006 Christian Gross http://www.devspace.com
    From the book Ajax Patterns and Best Practices

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
*/
function Asynchronous( ) {
    this._xmlhttp = new FactoryXMLHttpRequest();
    this.username = null;
    this.password = null;
}

function Asynchronous_call( action, url, data) {
    var instance = this;
    this._xmlhttp.open( action, url, true, this.username, this.password);
    this.openCallback( this._xmlhttp);
    this._xmlhttp.onreadystatechange = function() {
        switch( instance._xmlhttp.readyState) {
        case 1:
            instance.loading();
            break;
        case 2:
            instance.loaded();
            break;
        case 3:
            instance.interactive();
            break;
        case 4:
            if( instance.complete) {
                try {
                    instance.complete(instance._xmlhttp.status, instance._xmlhttp.statusText,
                            instance._xmlhttp.responseText, instance._xmlhttp.responseXML);
                }
                catch (e) {
                }
            }
            break;
        }
    }
    try {
        this._xmlhttp.send( data);
    }
    catch( e) {
    }
}

function Asynchronous_get( url) {
    this.call( "GET", url, null);
}

function Asynchronous_put( url, mimetype, datalength, data) {
    this.openCallback = function( xmlhttp) {
        xmlhttp.setRequestHeader( "Content-type", mimetype);
        xmlhttp.setRequestHeader( "Content-Length", datalength);
    }
    this.call( "PUT", url, data);
}

function Asynchronous_delete( url) {
    this.call( "DELETE", url, null);
}

function Asynchronous_post( url, mimetype, datalength, data) {
    var thisReference = this;
    this.userOpenCallback = this.openCallback;
    this.openCallback = function( xmlhttp) {
        xmlhttp.setRequestHeader( "Content-type", mimetype);
        xmlhttp.setRequestHeader( "Content-Length", datalength);
        thisReference.userOpenCallback( xmlhttp);
        thisReference.openCallback = thisReference.userOpenCallback;
    }
    this.call( "POST", url, data);
}

function Asynchronous_openCallback( obj) { 
}
function Asynchronous_loading() {
}
function Asynchronous_loaded() {
}
function Asynchronous_interactive() {
}
function Asynchronous_complete( status, statusText, responseText, responseHTML) {
}

Asynchronous.prototype.openCallback = Asynchronous_openCallback;
Asynchronous.prototype.loading = Asynchronous_loading;
Asynchronous.prototype.loaded = Asynchronous_loaded;
Asynchronous.prototype.interactive = Asynchronous_interactive;
Asynchronous.prototype.complete = Asynchronous_complete;
Asynchronous.prototype.get = Asynchronous_get;
Asynchronous.prototype.put = Asynchronous_put;
Asynchronous.prototype.del = Asynchronous_delete;
Asynchronous.prototype.post = Asynchronous_post;

Asynchronous.prototype.call = Asynchronous_call;

