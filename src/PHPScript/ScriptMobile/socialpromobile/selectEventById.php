<?php
require_once('connect.php');

$id=$_GET['id'];
$emarray = array();
$sql = "SELECT * FROM evenement where id=$id";
$result = mysqli_query($conn,$sql);

if ($result->num_rows > 0) {
    // output data of each row
	echo('{"events":[');
    while($row=mysqli_fetch_assoc($result)) {
      	$emarray = $row;
	echo json_encode($emarray);
       }
	echo(']}');
	
} else {
    echo "0 results";
}
$conn->close();
	 
?>
