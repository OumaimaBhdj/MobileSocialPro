<?php
require_once('connect.php');

$usr=$_GET['usr'];
$sql = "SELECT * FROM `fos_user` WHERE `username`='$usr' and enable=1";
$res = mysqli_query($conn,$sql);
$emarray = Array();

if ($res->num_rows > 0) {
    // output data of each row
	echo('{"user":[');
    while($row=mysqli_fetch_assoc($res)) {
      	$emarray = $row;
	echo json_encode($emarray);
       }
	echo(']}');
	
} else {
    echo "0 results";
}

mysqli_close($conn);
?>