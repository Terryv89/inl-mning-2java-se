import org.example.Accounts;
import org.example.Bank;
import org.example.Bankomat;
import org.example.User;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BankomatTesting {


    @Test
    public  void loginTest(){

        Bank bankmock = mock(Bank.class);
        Bankomat bankomat = new Bankomat(bankmock);

        ArrayList<User> userArrayList = new ArrayList<>();
        ArrayList<Accounts> skurtsAccount = new ArrayList<>();
        User skurt = new User("skurt", "1234", skurtsAccount);
        userArrayList.add(skurt);
        skurtsAccount.add(new Accounts("1337", 2000));

        when(bankmock.getUsers()).thenReturn(userArrayList);
        User actual = bankomat.login("skurt","1234");

        assertEquals(skurt, actual);
    }

    @Test
    public void loginWrongName(){

        Bank bankmock = mock(Bank.class);
        Bankomat bankomat = new Bankomat(bankmock);

        ArrayList<User> userArrayList = new ArrayList<>();
        ArrayList<Accounts> skurtsAccount = new ArrayList<>();
        User skurt = new User("Skurt", "1234", skurtsAccount);
        userArrayList.add(skurt);
        skurtsAccount.add(new Accounts("1337", 2000));

        when(bankmock.getUsers()).thenReturn(userArrayList);
        User actual = bankomat.login("skurt","1234");

        assertNull(actual);
    }

    @Test
    public void loginWrongPassword(){

        Bank bankmock = mock(Bank.class);
        Bankomat bankomat = new Bankomat(bankmock);

        ArrayList<User> userArrayList = new ArrayList<>();
        ArrayList<Accounts> skurtsAccount = new ArrayList<>();
        User skurt = new User("skurt", "1234", skurtsAccount);
        userArrayList.add(skurt);
        skurtsAccount.add(new Accounts("1337", 2000));

        when(bankmock.getUsers()).thenReturn(userArrayList);
        User actual = bankomat.login("skurt","12345");

        assertNull(actual);
    }

    @Test
    public void withdrawMoney(){

        Bank bankmock = mock(Bank.class);
        Bankomat bankomat = new Bankomat(bankmock);

        ArrayList<Accounts> testAccount = new ArrayList<>();
        testAccount.add(new Accounts("1336.9",2000));
        User skurt = new User("skurt","grön",testAccount);

        int expected = bankomat.withdrawMoney(300,skurt,"1336.9");

        assertEquals(expected,1700);
    }

    @Test
    public void withdrawToMuchMoney(){

        Bank bankmock = mock(Bank.class);
        Bankomat bankomat = new Bankomat(bankmock);

        ArrayList<Accounts> testAccount = new ArrayList<>();
        testAccount.add(new Accounts("1336.9",2000));
        User skurt = new User("skurt","grön",testAccount);

        int expected = bankomat.withdrawMoney(2500,skurt,"1336.9");

        assertEquals(expected,-500);
    }

    @Test
    public void withdrawWrongAccountNumber(){

        Bank bankmock = mock(Bank.class);
        Bankomat bankomat = new Bankomat(bankmock);

        ArrayList<Accounts> testAccount = new ArrayList<>();
        testAccount.add(new Accounts("1336.9",2500));
        User skurt = new User("skurt","grön",testAccount);

        int expected = bankomat.withdrawMoney(2500,skurt,"1337");

        assertEquals(expected,-1);
    }

    @Test
    public void depositMoneyWorks(){

        Bank bankmock = mock(Bank.class);
        Bankomat bankomat = new Bankomat(bankmock);

        ArrayList<User> userArrayList = new ArrayList<>();
        ArrayList<Accounts> skurtsAccount = new ArrayList<>();
        skurtsAccount.add(new Accounts("1337", 2000));
        User skurt = new User("skurt", "123", skurtsAccount);
        userArrayList.add(skurt);

        int expected = bankomat.depositMoney(500, skurt,"1337");

        assertEquals(expected,2500);

    }
    @Test
    public void depositMoneyWrongAccountNr(){

        Bank bankmock = mock(Bank.class);
        Bankomat bankomat = new Bankomat(bankmock);

        ArrayList<User> userArrayList = new ArrayList<>();
        ArrayList<Accounts> skurtsAccount = new ArrayList<>();
        skurtsAccount.add(new Accounts("1337", 2000));
        User skurt = new User("skurt", "123", skurtsAccount);
        userArrayList.add(skurt);

        assertThrows(RuntimeException.class,() -> {bankomat.depositMoney(500,skurt,"1234");});
    }

    @Test
    public void depositMinusValue(){

        Bank bankmock = mock(Bank.class);
        Bankomat bankomat = new Bankomat(bankmock);

        ArrayList<User> userArrayList = new ArrayList<>();
        ArrayList<Accounts> skurtsAccount = new ArrayList<>();
        skurtsAccount.add(new Accounts("1337", 2000));
        User skurt = new User("skurt", "123", skurtsAccount);
        userArrayList.add(skurt);

        assertThrows(RuntimeException.class,() -> {bankomat.depositMoney(-500,skurt,"123");});

    }

    @Test
    public void checkAccountBalance(){

        Bankomat bankomat = new Bankomat();

        ArrayList<User> userArrayList = new ArrayList<>();
        ArrayList<Accounts> skurtsAccount = new ArrayList<>();
        skurtsAccount.add(new Accounts("1337", 2000));
        User skurt = new User("skurt", "123", skurtsAccount);
        userArrayList.add(skurt);


        int expected = bankomat.checkAccountBalance("1337", skurt);

        assertEquals(expected,2000);
    }

    @Test
    public void CheckAccountBalanceNull(){

        Bankomat bankomat = new Bankomat();

        ArrayList<User> userArrayList = new ArrayList<>();
        ArrayList<Accounts> skurtsAccount = new ArrayList<>();
        skurtsAccount.add(new Accounts("1337", 2000));
        User skurt = new User("skurt", "123", skurtsAccount);
        userArrayList.add(skurt);

        assertThrows(NullPointerException.class,() -> {bankomat.checkAccountBalance("1337", null);});
    }

    @Test
    public void checkAccountBalanceWrongAccount(){

        Bankomat bankomat = new Bankomat();

        ArrayList<User> userArrayList = new ArrayList<>();
        ArrayList<Accounts> skurtsAccount = new ArrayList<>();
        skurtsAccount.add(new Accounts("1337", 2000));
        User skurt = new User("skurt", "123", skurtsAccount);
        userArrayList.add(skurt);

        int expected = bankomat.checkAccountBalance("1338", skurt);

        assertEquals(expected,-1);
    }
}