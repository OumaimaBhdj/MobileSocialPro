<?php
require_once('connect.php');


$emarray = array();
$sql = "SELECT * FROM evenement where datedebut> CURRENT_DATE and flag=1 order by datedebut ASC";
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
