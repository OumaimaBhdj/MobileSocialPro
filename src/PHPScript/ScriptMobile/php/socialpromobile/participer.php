<?php
require_once('connect.php');

$idevenement=$_GET['idevenement'];
$userid=$_GET['userid'];
$nbrelimite=$_GET['nbrelimite'];
$nbrparticipants=$_GET['nbrparticipants'];

$sql= "INSERT INTO participant (idevenement,user_id) VALUES($idevenement,$userid)";
$sql2="UPDATE evenement SET nbrelimite=$nbrelimite-1, nbrparticipants=$nbrparticipants+1 WHERE id=$idevenement";


if (mysqli_query($conn, $sql)) {
    if (mysqli_query($conn, $sql2)) {
    	
    	$emarray = array();
		$sql = "SELECT * FROM evenement where id=$idevenement";
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


	} else {
	    echo "Error: " . $sql2 . "<br>" . mysqli_error($conn);
	}
} else {
    echo "Error: " . $sql . "<br>" . mysqli_error($conn);
}







mysqli_close($conn);
?>