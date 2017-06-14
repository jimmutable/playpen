<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <title>Login Page</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css" integrity="sha384-rwoIResjU2yc3z8GV/NPeZWAv56rSmLldC3R/AZzGRnGxQQKnKkoFVhFQhNUwEyJ" crossorigin="anonymous">

 
	

  </head>
  

  <body>
    <div class="container" ng-app="myApp" ng-controller="myCtrl">

      <div class="d-flex flex-row justify-content-center align-middle" style="padding-top: 100px">
      <div class="col-lg-5 card card-block" >

	      <h4>Welcome to Digital Panda!</h4>
	      <form action="/do-login.html">
	      <fieldset class="form-group">
	      <label for="username">Email Address</label>
	      <input type="text" class="form-control" id="username" ng-model="formData.username">
	      </fieldset>
	      <fieldset class="form-group">
	      <label for="password">Password</label>
	      <input type="password" class="form-control" id="password" ng-model="formData.password">
	      </fieldset>
	
	      <div class="d-flex justify-content-end">
		  <div class="mr-auto p-2">Need an account? <a href="#">Sign up Today</a></div>
		  <div class="p-2"><button type="button" class="btn btn-default" ng-click="DoLogin()">Login</button></div>
        
      </div>

      </form>

    </div>
    </div>

    </div>

  <jsp:include page="/common/every-page-scripts.html" />
   
   <script>
   
   		var app = angular.module('myApp', []);
    	
		app.controller('myCtrl', function($scope, $http) 
		{
			$scope.formData = {};
		    
		    var loginSuccess = function(response)
		    {
		    	alert('Login Success: '+response.data.token);
		   	}
		   	
		   	var loginFailure = function(response)
		   	{
		   		alert('Login Failed: '+response.data.failure_message);
		   	}
		    
		    $scope.DoLogin = function()
		    {
		    	var res = $http({
		    		method: "get",
		    		url: "/api/1.0/auth/get-token.html",
		    		params: $scope.formData
		    		}).then(loginSuccess, loginFailure);
		    }
		});
		    	
   
   </script>

  </body>
</html>
