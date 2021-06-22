package client.clientUtils;

import sharedClasses.utils.DescriptionForObject;
import sharedClasses.utils.User;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class UserControl {

    public User logIn(String login, String password) throws NoSuchAlgorithmException {
        if (password.equals("") || login.equals("")) return null;
        return new User(login, getEncodedPassword(password));
    }

    public DescriptionForObject getTypeOfUser(boolean newUser) {
        DescriptionForObject type;
        if (newUser) type = DescriptionForObject.NEW_USER;
        else type = DescriptionForObject.REG_USER;
        return type;
    }

    private String getEncodedPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-512");
        return getEncodedString(messageDigest.digest(("*&^mVLCf(#" + password).getBytes(StandardCharsets.UTF_8)));
    }

    private String getEncodedString(byte[] bytes) {
        StringBuilder password = new StringBuilder();
        for (byte byte1 : bytes) {
            String s = Integer.toHexString(byte1);
            try {
                password.append(s.substring(s.length() - 2));
            } catch (IndexOutOfBoundsException e) {
                password.append("0").append(s);
            }
        }
        return password.toString();
    }
}
