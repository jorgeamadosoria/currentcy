<html>
	<head>
		<link href="http://currentcy-jasr.rhcloud.com/dist/css/bootstrap.min.css" type="text/css" rel="stylesheet">
		<link href="http://currentcy-jasr.rhcloud.com/dist/css/bootstrap-social.css" type="text/css" rel="stylesheet">
		<link href="http://currentcy-jasr.rhcloud.com/dist/css/sb-admin-2.css" type="text/css" rel="stylesheet">
		<link href="http://currentcy-jasr.rhcloud.com/dist/css/currentcy.css" type="text/css" rel="stylesheet">
		<link href="http://currentcy-jasr.rhcloud.com/dist/css/font-awesome.min.css" type="text/css" rel="stylesheet">
	</head>
	<body>
	<#list snapshots as snapshotbycur>
	<div class="row">
	<div class="text-center col-md-offset-3 col-md-6">
        <div class="panel panel-default">
            <div class="panel-heading">
               ${currencies[snapshotbycur_index]}
            </div>
            <!-- /.panel-heading -->
            <div class="panel-body">
                <div class="table-responsive">
                    <table class="table table-striped">
                        <thead>
                            <tr>
                                <th>Name</th>
                                <th>Avg</th>
                                <th>Buy</th>
                                <th>Sell</th>
                                <th>Date</th>
                            </tr>
                        </thead>
                        <tbody>
                        <#list snapshotbycur as snapshot>
                            <tr>
							    <td>${snapshot.name}</td>
							    <td>${snapshot.avg}</td>
							    <td>${snapshot.buy}</td>
								<td>${snapshot.sell}</td>
								<td>${snapshot.date}</td>
                            </tr>
						</#list>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
        </div>
        </div>
	</#list>
	</body>
</html>
