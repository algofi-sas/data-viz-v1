<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>Quandl</title>
		<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular.min.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.5.0/Chart.min.js"></script>
		<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
		<style>
			table, tr, td, th{
			border:1px solid ;
			}
			.inline-label{
			display: inline-block;
			}
			.block-label{
			display: block;
			padding: 0px 0px 5px 0px;
			}
			input, textarea, select{
			padding: 3px 10px;
			}
			select {
				height: 32.19px;
				width: 75%;
			}
		</style>
	</head>
	<body>
		<div ng-app="quandlTimeSeriesApp" ng-controller="quandlCtrl" class="w3-container">
			<div class="row">
				<div style="background-color: rgba(128, 128, 128, 0.5); width: 100%; height: 100%; margin: 0;top: 0; left: 0; position: absolute; z-index: 1;" ng-show="loading">
					<div style="position: absolute; top: 48%; left: 48%; font-size: xx-large;">Loading...</div>
				</div>
				<div class="w3-col">
					<h1 class="w3-center">Quandl Time Series API</h1>
					<hr />
					<form class="w3-container">
						<div class="w3-col m1 l1">&nbsp;</div>
						<div class="w3-col m2 l2">
							<label class="block-label">
							Database Code:
							</label>
							<input type="text" name="databaseCode" ng-model="databaseCode" />
							<label class="block-label">
							Dataset Code:
							</label>
							<input type="text" name="datasetCode" ng-model="datasetCode" />
						</div>
						<div class="w3-col m2 l2">
							<label class="block-label">
							Start Date:
							</label>
							<input type="date" name="startDate" ng-model="startDate" />
							<label class="block-label">
							End Date:
							</label>
							<input type="date" name="endDate" ng-model="endDate" />
						</div>
						<div class="w3-col m2 l2">
							<label class="block-label">
							Limit:
							</label>
							<input type="number" name="limit" ng-model="limit" min="0" />
							<label class="block-label">
							Column Index:
							</label>
							<input type="number" name="colIndex" ng-model="colIndex" min="-1" />
						</div>
						<div class="w3-col m2 l2">
							<label class="block-label">
							Order:
							</label>
							<select ng-model="order" ng-options="x for x in orderOptions"></select>
							<label class="block-label">
							Collapse:
							</label>
							<select ng-model="collapse" ng-options="x for x in collapseOptions"></select>
						</div>
						<div class="w3-col m2 l2">
							<label class="block-label">
							Transformation:
							</label>
							<select ng-model="transformation" ng-options="x for x in transformationOptions"></select>
							<label class="block-label">
							&nbsp;
							</label>
							<button ng-click="search()" class="w3-button w3-green w3-hover-green w3-round">Search</button>
							<button ng-click="clear()" class="w3-button w3-red w3-hover-red w3-round">Clear</button>
						</div>
					</form>
				<hr />
				</div>
				<table ng-show="succeded" id="myTable" class="w3-table-all w3-centered w3-striped w3-hoverable">
					<thead>
						<tr class="w3-light-grey">
							<th>Index</th>
							<th ng-repeat="column in requestResponse.column_names">{{ column }}</th>
						</tr>
					</thead>
					<tr ng-repeat="dataElement in requestResponse.data">
						<td><b>{{ $index+1 }}</b></td>
						<td ng-repeat="dataCol in dataElement track by $index">{{ dataCol }}</td>
					</tr>
					<thead>
						<tr class="w3-light-grey">
							<th>Total</th>
							<th colspan="{{requestResponse.column_names.length}}">{{ requestResponse.data.length }}</th>
						</tr>
					</thead>
				</table>
				<div style="width: 100%;" id="chartContainer" ng-show="succeded">
				</div>
			</div>
		</div>
		<script src="./quandl/angular/controller/quandlTimeSeriesController.js"></script>
	</body>
</html>