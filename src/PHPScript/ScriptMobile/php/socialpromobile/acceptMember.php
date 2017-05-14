<?php
require_once('connect.php');
$id=$_GET['id'];

$sql = "UPDATE `fos_user` SET `enable`=1 WHERE id =$id";

if (mysqli_query($conn, $sql)) {
    	 echo "success";
} else {
    echo "Error";
}

mysqli_close($conn);
?>