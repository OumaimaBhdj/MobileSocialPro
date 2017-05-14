<?php
require_once('connect.php');
$id=$_GET['id'];
$sql = "select * FROM `Message` where `user_id`=$id";
$result = $conn->query($sql);
$json = new SimpleXMLElement('<xml/>'); 
if ($result->num_rows > 0) {
    // output data of each row
        while($row = $result->fetch_assoc()) {
        $mydata = $json->addChild('message');
        $mydata->addChild('id',$row['id']);
		$mydata->addChild('text',$row['text']);
		$mydata->addChild('datedenvoye',$row['datedenvoye']);
		
         }
} else {
    echo "0 results";
}
$conn->close();
	 echo( json_encode ($json));
?>
