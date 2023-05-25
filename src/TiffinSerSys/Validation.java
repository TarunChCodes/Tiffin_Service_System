package TiffinSerSys;

public class Validation {

    public boolean nameValidate(String name){

        boolean flag = true;

        if(name.length() == 0) {

            flag = false;

        } else {

            for(int i=0; i<name.length(); i++){

                if( !((name.charAt(i) >= 65 && name.charAt(i) <= 90) || (name.charAt(i) >= 97 && name.charAt(i) <= 122) || (name.charAt(i) == ' ') || (name.charAt(i) == '.') ) ) {
                    flag = false;
                    break;
                }

            }

        }

        return flag;
    }

    public boolean usrnameValidate(String usrName){

        boolean flag = true;

        if(usrName.length() == 0 ) {

            flag = false;

        } else {

            for(int i=0; i<usrName.length(); i++){

                if(!( (usrName.charAt(i) >=65 && usrName.charAt(i) <=90) || (usrName.charAt(i) >=97 && usrName.charAt(i) <=122) || (usrName.charAt(i) >= 48 && usrName.charAt(i) <= 57) || (usrName.charAt(i) == '_') ) ) {
                    flag = false;
                }

            }
        }

        return flag;
    }

    public boolean passValidate(String pass) {

        boolean flag = true;
        int u_Count = 0, l_Count = 0, num_Count = 0, s_Count = 0;


        if(pass.length() > 8 ){

            for(int i=0; i<pass.length(); i++) {

                if(pass.charAt(i)>=65 && pass.charAt(i)<90) {
                    u_Count++;
                }
                else if(pass.charAt(i)>=97 && pass.charAt(i)<122) {
                    l_Count++;
                }
                else if(pass.charAt(i)>=46 && pass.charAt(i)<57) {
                    num_Count++;
                }
                else if((pass.charAt(i)>=33 && pass.charAt(i)<64) || pass.charAt(i) == '@' ) {
                    s_Count++;
                }

            }

        } else {
            flag = false;
        }

        if( u_Count == 0  || l_Count == 0 || num_Count == 0 || s_Count == 0  ) {
            flag = false;
        }


        return flag;
    }

    public boolean confirmPassValidate(String cpass, String pass) {

        boolean flag = false;

        if(cpass.equals(pass)) {
            flag = true;
        }

        return flag;
    }

    public boolean contactValidate(String contact){

        boolean flag = true;

        int len = contact.length();
        if(len != 10){
            flag =false;
        } else {
            for (int i = 0; i < len; i++) {
                if ((contact.charAt(i) < 46 && contact.charAt(i) > 57)) {
                    flag = false;
                    break;
                }
            }
        }

        return flag;
    }

    public boolean emailValidate(String email) {

        boolean flag = true;

        if(email.length()>6){
            for(int i=0; i<email.length(); i++){
//                if the char is not belong to Alphabet or number then set flag to FALSE else its good to go
                if( !( (email.charAt(i) >=65 && email.charAt(i) <=90) || (email.charAt(i) >= 97 && email.charAt(i) <=122 ) || (email.charAt(i) >= 48 && email.charAt(i) <=57 ) )) {
                    flag = false;
                }
            }
        } else {
            flag = false;
        }
        return flag;
    }

    public boolean houseValidate (String houseNo){
        boolean flag = true;

        // only alphanumeric and space are allowed

        if(houseNo.length() != 0){
            for(int i=0; i<houseNo.length(); i++) {
                if(!( (houseNo.charAt(i) >= 65 && houseNo.charAt(i) <=90 ) || (houseNo.charAt(i) >=97 && houseNo.charAt(i) <=122) || (houseNo.charAt(i) >= 47 && houseNo.charAt(i) <=57 ) || (houseNo.charAt(i) == 32) || (houseNo.charAt(i) == ',') ) ) {
                    flag = false;
                    break;
                }
            }
        } else {
            flag = false;
        }

        return flag;
    }

    public boolean colonyValidate (String colony){
        boolean flag = true;

//        Only Alphabet and Space is allowed
        if(colony.length() != 0){
            for(int i=0; i<colony.length(); i++) {
                if(!( (colony.charAt(i) >= 65 && colony.charAt(i) <=90 ) || (colony.charAt(i) >=97 && colony.charAt(i) <=122) || (colony.charAt(i) == 32) ) ) {
                    flag = false;
                    break;
                }
            }
        } else {
            flag = false;
        }

        return flag;
    }

    public boolean cityValidate (String city){
        boolean flag = true;

//        Only Alphabet and Space is allowed
        if(city.length() != 0){
            for(int i=0; i<city.length(); i++) {
                if(!( (city.charAt(i) >= 65 && city.charAt(i) <=90 ) || (city.charAt(i) >=97 && city.charAt(i) <=122) || (city.charAt(i) == 32) ) ) {
                    flag = false;
                    break;
                }
            }
        } else {
            flag = false;
        }

        return flag;
    }

    public boolean stateValidate (String state){
        boolean flag = true;

//        Only Alphabet and Space, dot(.) is allowed
        if(state.length() != 0){
            for(int i=0; i<state.length(); i++) {
                if(!( (state.charAt(i) >= 65 && state.charAt(i) <=90) || (state.charAt(i) >=97 && state.charAt(i) <=122) || (state.charAt(i) == 32) || state.charAt(i) == '.') ) {
                    flag = false;
                    break;
                }
            }
        } else {
            flag = false;
        }

        return flag;
    }

    public boolean picodeValidate (String pincode){
        boolean flag = true;

//        Only number is allowed
        if(pincode.length() != 0){
            for(int i=0; i<pincode.length(); i++) {
                if(!( pincode.charAt(i)>=48 && pincode.charAt(i)<=57 ) ) {
                    flag = false;
                    break;
                }
            }
        } else {
            flag = false;
        }

        return flag;
    }

    public boolean addressValidate (String addStr) {

        boolean flag = true;

        if(addStr.length()!=0) {
            for(int i=0; i<addStr.length(); i++) {
                if (!( (addStr.charAt(i) >= 65 || addStr.charAt(i)<=90 ) || ( addStr.charAt(i) >= 97 || addStr.charAt(i)<=122) || (addStr.charAt(i)>=48 && addStr.charAt(i)<=57) )) {
                    flag = false;
                }
            }
        } else {
            flag = false;
        }

        return flag;
    }

    public static void main(String[] args) {
        new Validation();
    }
}
