package com.cbd.backend.service.impl;

import com.cbd.backend.database.UserRepository;
import com.cbd.backend.model.Authority;
import com.cbd.backend.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl {

    private final UserRepository userRepository;

    User addUser (String username, String password, String firstName, String lastName, String email, String account, long passwordUpdateDate, long timestamp, long id, com.sun.tools.javac.util.List<Authority> authorizations ) {
        return null;
    }

    @Autowired
    public  UserServiceImpl ( @Qualifier("userRepository") UserRepository repo) {
        this.userRepository = repo;
    }


    public User addStockUser() {
        long timestamp = System.currentTimeMillis();
        User u = new User();
        List<Authority> authorities = new ArrayList<Authority>();
        authorities.add(new Authority("admin" , true) );


        u.setAuthory( authorities );
        u.setUsername( "wvoelkl" );
        u.setAccount( "password" );
        u.setFirstName( "Warren" );
        u.setLastName( "Voelkl" );
        u.setEmail( "warrenvoelkl@gmail.com" );
        u.setAccount( "BugsSoftware" );
        u.setPasswordUpdateDate( timestamp );
        u.setLastUpdated( timestamp );
        u.setId( timestamp );

        User result = userRepository.save( u );
        return u;

//        Type listType = new TypeToken<List<Inventory>>() {}.getType();

        //inventoryRepository.deleteAll();

//        List<Inventory> inventories = gson.fromJson(jsonString, listType );
//        for(Inventory inventory : inventories) {
//            inventory.setLastUpdated(dateTime);
//            inventoryRepository.save(inventory);
//            System.out.println(inventory);
//        }
}



    //TODO determine if jwt already hashes password
//    public static byte[] hashPassword( final char[] password, final byte[] salt, final int iterations, final int keyLength ) {
//
//        try {
//            SecretKeyFactory skf = SecretKeyFactory.getInstance( "PBKDF2WithHmacSHA512" );
//            PBEKeySpec spec = new PBEKeySpec( password, salt, iterations, keyLength );
//            SecretKey key = skf.generateSecret( spec );
//            byte[] res = key.getEncoded( );
//            return res;
//
//        } catch( NoSuchAlgorithmException | InvalidKeySpecException e ) {
//            throw new RuntimeException( e );
//        }
//    }
}
