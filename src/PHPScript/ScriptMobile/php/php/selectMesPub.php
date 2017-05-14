<?php
require_once('connect1.php');

$id=$_GET['id'];

$sql = "SELECT * FROM `publication` where `user_id`='$id' ";
$result = $conn->query($sql);
$json = new SimpleXMLElement('<xml/>');
if ($result->num_rows > 0) {
    // output data of each row
		while($row = $result->fetch_assoc()) {
       $mydata = $json->addChild('publication');
      $mydata->addChild('id',$row['id']);
	 
		 $mydata->addChild('text',$row['text']);
		 $mydata->addChild('path',$row['path']);
		 $mydata->addChild('username',$row['pubusername']);
		 $mydata->addChild('jaime',$row['jaime']);
		 
		 $mydata->addChild('jaimePas',$row['jaimepas']);
		 $mydata->addChild('dateAjout',$row['date']);
         }
} else {
    echo "0 results";
}
$conn->close();
	 echo( json_encode ($json));
?>