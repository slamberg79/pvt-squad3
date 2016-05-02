
var ReactTransitionGroup = React.addons.CSSTransitionGroup;
var token = $("meta[name='_csrf_token']").attr("content");
var header = $("meta[name='_csrf_header']").attr("content");
var parameter = $("meta[name='_csrf_parameter']").attr("content");
var usrname;
var passwd;
$("body").bind("ajaxSend", function(elm, xhr, s){
   if (s.type == "POST") {
		console.log("TEST in bind");
		xhr.setRequestHeader(getCSRFHeader(), getCSRFToken());
   }
});

function getCSRFHeader() {
	return header;
}

function getCSRFToken() {
	return token;
}

function getCSRFParameter() {
	return parameter;
}

var LoginForm = React.createClass({
	
	getInitialState: function() {
		return { mounted: false, nameInput: '', passwordInput: ''};
	},
	handleChangeName: function(event) {
        this.setState({nameInput: event.target.value});
    },
    /* Gets called when the second input field changes */
    handleChangePassword: function (event) {
        this.setState({passwordInput: event.target.value});
    },
	
	componentDidMount: function() {
		this.setState({ mounted: true });
	},

    handleClickReg: function(){
        ReactDOM.render(<RegisterForm />, document.getElementById('loginPage'))
    },
    
    handleClickLogin: function(){       
        
        if(loginForm.Uname.value == "user" &&
           loginForm.Fpass.value == "password"){
            
            alert("Login SUCCESSFUL! but stay here for a while")
        }
        else
        {
            alert("Wrong username or password");
           
        }
        
    },
	
    submitLogin: function(){
    	console.log("TEST in submit: " + getCSRFParameter() );
    	$.ajax({
            type: "POST",
            url: "/user",
            data: JSON.stringify({ name : "Fpass", password : "Uname" }),
            contentType: "application/json; charset=utf-8",
            dataType: "json",
			beforeSend: function(xhr) {
				console.log("Before send!" + getCSRFToken());
				xhr.setRequestHeader("X-CSRF-Token", getCSRFToken());
			},
            success: function(data){console.log("SUCCESS");},
            failure: function(errMsg) {
                console.log("FAIL");
            }
        });
    },
	
	getStuff: function() {
		$.ajax({
            type: "GET",
            url: "/user",
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            success: function(data){
				console.log("AYYYYY" + data.name + " " + data.password);
                this.setState({nameResponse: data.name});
                this.setState({passwordResponse: data.password});
            }.bind(this),
            failure: function(errMsg) {
                alert(errMsg);
            }
        });
	},

    render: function(){
        return (
        <ReactTransitionGroup transitionName="example" 
                              transitionEnterTimeout={500}
                              transitionLeaveTimeout={300}>
         <div class="row">
            <h1>Student Capture</h1>
            </div>
            <div class="row">
                <div class="four columns">
                    <input class="u-full-width" type="text" 
                           placeholder="Username" id="Uname" name="username"/>
                </div>
            </div>
            <div class="row">
                <div class="four columns">
                    <input class="u-full-width" type="password" 
                           placeholder="Password" id="Fpass" name="password"/>
                </div>
            </div>
            <input type="hidden"
			name={getCSRFParameter()}
			value={getCSRFToken()} />
            <button type="submit" onClick={this.submitLogin}>
                Login
            </button>
            <button type="button" onClick={this.handleClickReg}>
                Register
            </button>
			<button type="button" onClick={this.getStuff}>
				TEST
			</button>
			<h1>{this.state.nameResponse}</h1>
            <h1>{this.state.passwordResponse}</h1>
       </ReactTransitionGroup>
        );
    }
});


var RegisterForm = React.createClass({

    handleClickRegister: function(){
        alert("Registration was very unsuccessful.... no server!!")
//        ReactDOM.render(<LoginForm />, document.getElementById('loginPage'))
    },
    
    handleClickCancel: function(){
        
        ReactDOM.render(<LoginForm />, document.getElementById('loginPage'))
    },
    

    render: function() {
        return (
            <div>
                
                <h1>Registration page</h1>
                <div class="row">
                    <div class="four columns">
                        <input class="u-full-width" type="text" 
                               placeholder="First name" id="Fname" />
                    </div>
                </div>
                <div class="row">
                    <div class="four columns">
                        <input class="u-full-width" type="text" 
                               placeholder="Last name" id="Lname" />
                    </div>
                </div>
                <div class="row">
                    <div class="four columns">
                        <input class="u-full-width" type="email" 
                               placeholder="example@mail.com" id="EmailInput" />
                    </div>
                </div>
                <div class="row">
                    <div class="four columns">
                        <input class="u-full-width" type="text" 
                               placeholder="Username" id="Uname" />
                    </div>
                </div>
                <div class="row">
                    <div class="four columns">
                        <input class="u-full-width" type="password" 
                               placeholder="Password" id="Fpass" />
                    </div>
                </div>
                <div class="row">
                    <div class="four columns">
                        <input class="u-full-width" type="password" 
                               placeholder="Repeat password" id="Spass" />
                    </div>
                </div>
 
                <button type="submit">
                    Register
                </button>
                <button type="button" onClick={this.handleClickCancel}>
                    Cancel
                </button>

            </div>
        );
    }
});


ReactDOM.render(
    <LoginForm />,
    document.getElementById('loginPage')
);

//window.RegisterForm = RegisterForm;
