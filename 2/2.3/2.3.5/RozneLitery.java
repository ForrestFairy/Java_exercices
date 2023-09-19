public class RozneLitery {
    
    public static void main(String[] args) {
        StringBuffer rozne = new StringBuffer("");
        String napis = args[0];
        boolean nowy;
        for (int i = 0; i < napis.length(); i++) {
            nowy = true;
            if (Character.isLetter(napis.charAt(i))) {
                for (int j = 0; j < rozne.length(); j++) {
                    if (napis.charAt(i) == rozne.charAt(j)) {
                        nowy = false;
                        break;
                    }
                }
                if (nowy) rozne.append(napis.charAt(i));
            }
            

        }
        System.out.println(rozne);
        System.out.println(rozne.length());
    }
}
