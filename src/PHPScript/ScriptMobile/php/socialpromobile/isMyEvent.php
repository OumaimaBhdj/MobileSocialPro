<?php
require_once('connect.php');

$iduser=$_GET['iduser'];
$idevent=$_GET['idevent'];

$sql = "SELECT * FROM `evenment` WHERE `user_id` =$iduser and `idevenement`=$idevent";
$result = mysqli_query($conn,$sql);



if (mysqli_num_rows($result) > 0) {
    // output data of each row
	echo('ok');
} else {
    echo ("nok");
}
$conn->close();
	 
?>