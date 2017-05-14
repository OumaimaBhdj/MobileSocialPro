<?php
require_once('connect.php');

$idUser=$_GET['iduser'];
$emarray = array();
$sql = "SELECT * FROM evenement e , participant p where datedebut>CURRENT_DATE and e.id=p.idEvenement and p.user_id=$idUser";
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
