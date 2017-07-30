<html>
<head>
<script src="static/js/moduleEnv.js"></script>
<script src="static/js/env.js"></script>
<script src="static/js/role.js"></script>
</head>

<body>

	<form id="file_upload"  enctype="multipart/form-data" method="post" action="rest/svc/config/master/uploadExcelData">
		<input type="file" name="file" id="file" size="50" /><br>
		<br>
		<button name="submit" type="submit" onclick="uploadFile()">Upload</button>
		<div id="result"></div>
	</form>

	<fieldset>
		<input class="submit" type="button" value="Add new URL"
			id="newModuleEnv">
		</p>

		<div>
			<table id="moduleEnvDataTable" class="display cell-border"
				cellspacing="0" width="100%">
				<thead>
					<tr>
						<th>URL Id</th>
						<th>Name</th>
						<th>Description</th>
						<th>URL</th>
						<th>Username</th>
						<th>Password</th>
						<th>Module Name</th>
						<th>Env Name</th>
						<th>Visible to Role</th>
						<th>Visible</th>
						<th>Email</th>
						<th>Created By</th>
						<th>Created On</th>
						<th>Updated By</th>
						<th>Updated On</th>
					</tr>
				</thead>
				<tbody>
				</tbody>
			</table>
		</div>

	</fieldset>



	<div id="addModifyModuleEnv" style="display: none;"
		title="Add/Modify URL">
		<form class="moduleEnvForm" id="moduleEnvForm" method="post" action="">
			<fieldset>
				<table width="100%">
					<tbody>
						<tr>
							<td>
								<h4>
									<strong>URL Name</strong>
								</h4>
								<p>
									<input name="urlName" type="text" value="" required />
								</p>
							</td>
							<td>
								<h4>
									<strong>Module Name</strong>
								</h4>
								<p>

									<select name="moduleId" id="moduleId">
									</select>

								</p>
							</td>
						</tr>
						<tr>
							<td>
								<h4>
									<strong>Description</strong>
								</h4>
								<p>
									<input name="urlDesc" type="text" value="" required />
								</p>
							</td>
							<td>
								<h4>
									<strong>Environment Name</strong>
								</h4>
								<p>

									<select name="envId" id="envId">
									</select>

								</p>
							</td>
						</tr>

						<tr>
							<td>
								<h4>
									<strong>URL</strong>
								</h4>
								<p>
									<input name="url" type="text" value="" required />
								</p>
							</td>
							<td>
								<h4>
									<strong>Visible to Role</strong>
								</h4>
								<p>

									<select name="roleId" id="roleId">
									</select>

								</p>
							</td>
						</tr>
						<tr>
							<td>
								<h4>
									<strong>User Name</strong>
								</h4>
								<p>
									<input name="userName" type="text" value="" />
								</p>
							</td>
							<td>
								<h4>
									<strong>Visible to All</strong>
								</h4>
								<p>

									<select name="visibility" id="visibility">
										<option value="true">YES</option>
										<option value="false">NO</option>
									</select>

								</p>
							</td>
						</tr>
						<tr>
							<td>
								<h4>
									<strong>Password</strong>
								</h4>
								<p>
									<input name="password" type="text" value="" />
								</p>
							</td>
							<td>
								<h4>
									<strong>Email</strong>
								</h4>
								<p>
									<input name="email" type="text" value="" />
								</p>
							</td>
						</tr>
					</tbody>
				</table>
			</fieldset>
			<input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}" />
		</form>
	</div>

	<script>
		//$("#addModifyModuleEnv").validate();
	</script>
</body>
</html>
