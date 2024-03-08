class RequestBox {
    constructor(element) {
        this.element = element;
        this.inputs = new Map([...element.querySelectorAll("input")].map(inputEl => [inputEl.id, inputEl]));
        this.keys = new Map([...this.inputs].map(([id]) => [id, element.querySelector(".key-" + id)]));
        this.values = new Map([...this.inputs].map(([id]) => [id, element.querySelector(".value-" + id)]));
        this.submit = element.querySelector(".submit");
        this.resultBox = element.querySelector(".result-box");
        this.result = this.resultBox.querySelector(".result");

        this.baseurl = element.querySelector(".request-baseurl").textContent;
    }

    init() {
        this.inputs.forEach((inputEl) => {
            inputEl.addEventListener("input", () => {
                this.values.get(inputEl.id).textContent = inputEl.value;
            });
        });
        
        this.submit.addEventListener("click", async () => {
            this.updateResult(await this.submitRequest());
        });
    }

    async submitRequest() {
        const requestParams = {};
        this.keys.forEach((keyEl, id) => {
            if(this.inputs.get(id).value != undefined && this.inputs.get(id).value != "")
                requestParams[keyEl.textContent] = this.inputs.get(id).value;
        });

        const result = await fetch(this.baseurl + "?" + new URLSearchParams(requestParams));
        const resultJson = await result.json();
        this.afterSubmit(resultJson);
        return resultJson;
    }

    afterSubmit(resultJson) {
        // Overwrite this method to use it
    }

    isResultBoxVisible() {
        return !this.resultBox.classList.contains("result-box-hidden");
    }

    switchResultBoxVisibility() {
        this.resultBox.classList.toggle("result-box-hidden");
    }

    updateResult(jsonObj) {
        if(!this.isResultBoxVisible())
            this.switchResultBoxVisibility();
        this.result.textContent = JSON.stringify(jsonObj, null, 2);
    }

    updateInput(id, value) {
        this.inputs.get(id).value = value;
        this.values.get(id).textContent = value;
    }
}


const requestBoxes = new Map([...document.querySelectorAll('.request-box')].map(element => [element.id, new RequestBox(element)]));

requestBoxes.forEach((requestBox) => requestBox.init());

if(requestBoxes.has("prompt-access")) {
    const promptAccessBox = requestBoxes.get("prompt-access");

    promptAccessBox.afterSubmit = (result) => {
        // Fill extracted url in prompt-access (Auth module)
        const extractedLink = promptAccessBox.element.querySelector(".extracted-link a");
        extractedLink.href = result.authorizationUrl;
        extractedLink.textContent = result.authorizationUrl;

        // Fill inputs in request-token from prompt-access result (Auth module)
        if(requestBoxes.has("request-token")) {
            const requestTokenBox = requestBoxes.get("request-token");
            requestTokenBox.updateInput("requestToken", result.requestToken);
            requestTokenBox.updateInput("tokenSecret", result.tokenSecret);
        }
    };
}

//Gets request token, directs to usos login
function login() {
    fetch('/auth/request-token')
        .then(response => response.json())
        .then(result => {
            sessionStorage.setItem('requestToken', result.requestToken);
            sessionStorage.setItem('requestTokenSecret', result.tokenSecret);

            window.location.href = result.authorizationUrl;
        });
}

//Handles redirect from successful usos login
function checkParams() {
    const urlParams = new URLSearchParams(window.location.search);
    if (urlParams.has("oauth_verifier")) {

        const accessRequest = {
            requestToken: sessionStorage.getItem('requestToken'),
            tokenSecret: sessionStorage.getItem('requestTokenSecret'),
            verifier: urlParams.get("oauth_verifier")
        };

        //Logins user and redirects to home page
        fetch(`/auth/access-token`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(accessRequest)
        })
            .then(response => response.json())
            .then(result => document.cookie = `JWT=${result.token}`)
            .then(result => window.location.href = '/');
    }
}

function requestUser() {
    const result = document.getElementById("resultUser");
    fetch(`/userapi/user`)
        .then(response => response.json())
        .then(response => {
            result.textContent = JSON.stringify(response, null, 2)
        })
}