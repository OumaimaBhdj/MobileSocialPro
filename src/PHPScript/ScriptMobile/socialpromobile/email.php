<?php
require_once('connect.php');

$email=$_GET['email'];
$sql = "SELECT * from fos_user where email = '$email'";
$result = mysqli_query($conn,$sql);
$s = mysqli_num_rows($result);
if ($s > 0) {
    // output data of each row
	echo("used");
} else {
    echo ("not used");
}
$conn->close();
	 
?>