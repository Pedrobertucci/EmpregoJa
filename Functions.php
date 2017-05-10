<?php
 
include 'DBOperations.php';
 
class Functions{
 
private $db;
 
public function __construct() {
 
      $this-> db = new DBOperations();
 
}
 
public function registerUser($name, $sobrenome, $email,$password) {
 
   $db = $this -> db;
 
   if (!empty($name)  && !empty($sobrenome) && !empty($email) && !empty($password)) {
 
      if ($db -> checkUserExist($email)) {
 
         $response["result"] = "failure";
         $response["message"] = "Usuario já cadastrado!";
         return json_encode($response);
 
      } else {
 
         $result = $db -> insertData($name,$sobrenome,$email,$password);
 
         if ($result) {
 
            $response["result"] = "success";
            $response["message"] = "Usuario registrado com sucesso! Nome: ".$name;
            return json_encode($response);
 
         } else {
 
            $response["result"] = "failure";
            $response["message"] = "Falha no cadastrar!";
            return json_encode($response);
 
         }
      }
   } else {
 
      return $this -> getMsgParamNotEmpty($sobrenome);
 
   }
}
 
public function loginUser($email, $password) {
 
  $db = $this -> db;
 
  if (!empty($email) && !empty($password)) {
 
    if ($db -> checkUserExist($email)) {
 
       $result =  $db -> checkLogin($email, $password);
 
       if(!$result) {
 
        $response["result"] = "failure";
        $response["message"] = "Credenciais de login invalida! aqui ";
        return json_encode($response);
 
       } else {
 
        $response["result"] = "success";
        $response["message"] = "Login efetuado com sucesso!";
        $response["user"] = $result;
        return json_encode($response);
 
       }
    } else {
 
      $response["result"] = "failure";
      $response["message"] = "Credencais de login Invalida!";
      return json_encode($response);
 
    }
  } else {
 
      return $this -> getMsgParamNotEmpty();
    }
}
 
public function changePassword($email, $old_password, $new_password) {
 
  $db = $this -> db;
 
  if (!empty($email) && !empty($old_password) && !empty($new_password)) {
 
    if(!$db -> checkLogin($email, $old_password)){
 
      $response["result"] = "failure";
      $response["message"] = 'Invalid Old Password';
      return json_encode($response);
 
    } else {
 
    $result = $db -> changePassword($email, $new_password);
 
      if($result) {
 
        $response["result"] = "success";
        $response["message"] = "Password Changed Successfully";
        return json_encode($response);
 
      } else {
 
        $response["result"] = "failure";
        $response["message"] = 'Error Updating Password';
        return json_encode($response);
 
      }
    }
  } else {
 
      return $this -> getMsgParamNotEmpty();
  }
}
 
public function isEmailValid($email){
 
  return filter_var($email, FILTER_VALIDATE_EMAIL);
}
 
public function getMsgParamNotEmpty($porque){
 
  $response["result"] = "failure";
  $response["message"] = "Os parâmetros não devem estar vazios!".$porque;
  return json_encode($response);
 
}
 
public function getMsgInvalidParam(){
 
  $response["result"] = "failure";
  $response["message"] = "Invalid Parameters";
  return json_encode($response);
 
}
 
public function getMsgInvalidEmail(){
 
  $response["result"] = "failure";
  $response["message"] = "Invalid Email";
  return json_encode($response);
 
}
}