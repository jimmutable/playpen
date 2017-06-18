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

	      <h4>Create your Digital Panda Account</h4>
	      <form action="/do-login.html">
	      
	      <fieldset class="form-group">
	      <label for="first_name">First Name*</label>
	      <input type="text" class="form-control" id="first_name" ng-model="formData.first_name">
	      
	      </fieldset>
	      
	      <fieldset class="form-group">
	      <label for="last_name">Last Name*</label>
	      <input type="text" class="form-control" id="last_name" ng-model="formData.last_name">
	      </fieldset>
	      
	      <fieldset class="form-group">
	      <label for="email_address">Email Address</label>
	      <input type="text" class="form-control" id="email_address" ng-model="formData.email_address">
	      </fieldset>
	      
	      <fieldset class="form-group">
	      <label for="title">Title</label>
	      <input type="text" class="form-control" id="title" ng-model="formData.title">
	      </fieldset>
	      
	      <fieldset class="form-group">
	      <label for="password">Create a password*</label>
	      <input type="password" class="form-control" id="password" ng-model="formData.password">
	      </fieldset>
	      
	      <fieldset class="form-group">
	      <label for="confirm_password">Confirm your password*</label>
	      <input type="password" class="form-control" id="confirm_password" ng-model="formData.confirm_password">
	      </fieldset>
	      
	      <fieldset class="form-group">
	      <label for="mobile_phone_number">Mobile Phone Number</label>
	      <input type="text" class="form-control" id="mobile_phone_number" ng-model="formData.mobile_phone_number">
	      </fieldset>
	      
	      <fieldset class="form-group">
	      <label for="work_phone_number">Work Phone Number</label>
	      <input type="text" class="form-control" id="work_phone_number" ng-model="formData.work_phone_number">
	      </fieldset>
	      
	      <fieldset class="form-group">
	      <label for="timezone">Timezone*</label>
	      <select class="form-control" name="timezone" id="timezone" ng-model="formData.timezone">
	      	<jsp:include page="/common/all-timezones.html" />
	      </select>
	      
	      </fieldset>
	      
	
	      <div class="d-flex justify-content-end">
		  <div class="mr-auto p-2">Need an account? <a href="/users/create-new-user-account.html">Sign up Today</a></div>
		  <div class="p-2"><button type="button" class="btn btn-default" ng-click="DoCreateUserAccount()">Create</button></div>
        
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
			$scope.formData.timezone = "US/Eastern";
		    
		    var formSuccess = function(response)
		    {
		    	alert('Create Success!'+response.data);
		    	console.log(response);
		   	}
		   	
		   	var formFailure = function(response)
		   	{
		   	 	if ( response.data.message == null )
		   	 		response.data.message = "Unknown reason, invalid data";
		   	 	
		   		alert('Account creation failed: '+response.data.message);
		   	}
		    
		    $scope.DoCreateUserAccount = function()
		    {
		    	if ( $scope.formData.password == null || $scope.formData.password.length < 5 || $scope.formData.password != $scope.formData.confirm_password )
		    	{
		    		alert('Passwords do not match or are less than 5 characters');
		    		return;
		    	}
		    
		    	var res = $http({
		    		method: "post",
		    		url: "/api/1.0/users/create-new-user-account.html",
		    		data: $scope.formData
		    		}).then(formSuccess, formFailure);
		    }
		});
		    	
   
   </script>

  </body>
</html>
