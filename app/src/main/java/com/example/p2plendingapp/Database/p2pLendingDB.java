package com.example.p2plendingapp.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.p2plendingapp.Model.Account;
import com.example.p2plendingapp.Model.Borrower;
import com.example.p2plendingapp.Model.Customer;
import com.example.p2plendingapp.Model.Loan;

public class p2pLendingDB extends SQLiteOpenHelper {

    //Set up references
    protected static final String DB_NAME = "P2pLending";
    protected static final int DB_VERSION = 1;

    //Account table details
    protected final String accountTB = "Account";
    protected final String aId = "AccountId";
    protected final String userName = "Username";
    protected final String password = "Password";
    protected final String eMail = "Email";
    protected final String acId = "CustomerId";

    //Borrower table details
    protected final String borrowerTB = "Borrower";
    protected final String bId = "BorrowerId";
    protected final String hmExp = "HouseholdExp";
    protected final String rStatus = "ResidencyStatus";
    protected final String mNetIncome = "NetIncome";
    protected final String mSNetIncome = "SpouseNetIncome";
    protected final String mStatus = "MaritalStatus";
    protected final String pStatus = "PropertyStatus";

    //Investor table details
    protected final String investorTB = "Investor";
    protected final String inId = "InvestorId";
    protected final String sOFunds = "SourceFunds";
    protected final String pRLevel = "RiskLevelPref";

    //Customer table details
    protected final String customerTB = "Customer";
    protected final String cId = "CustomerId";
    protected final String dOB = "D0B";
    protected final String fName = "FirstName";
    protected final String lName = "LastName";
//    protected final String cbAcc = "CanadianBankAcc";

    //Investment table details
    protected final String investmentTB = "Investment";
    protected final String iId = "InvestmentId";
    protected final String sFee = "ServiceFee";
    protected final String mEarnings = "Earnings";
    protected final String aDeal = "AcceptDeal";
    protected final String aTerms = "AgreeTerms";
    protected final String tLAmount = "LoanAmountTransferred";

    //Loan table details
    protected final String loanTB = "Loan";
    protected final String lId = "LoanId";
    protected final String pOBorrowing = "BorrowingPurpose";
    protected final String oFee = "OriginationFee";
    protected final String iRate = "InterestRate";
    protected final String uPFee = "UnpayFee";
    protected final String lPFee = "LateFee";
    protected final String mPAmount = "PaymentAmount";
    protected final String lAmount = "LoanAmount";
    protected final String sDOAgreement = "DateOfAgreement";
    protected final String pPeriod = "PaymentPeriod";

    //Transaction table details
    protected final String transactionTB = "Transaction";
    protected final String tId = "TransactionId";
    protected final String tDesc = "TransactionDesc";

    String[] tableNameArray;
    protected SQLiteDatabase myDb;
    protected long numRecordsInserted;
    protected int numRecordsDeleted;

    public p2pLendingDB(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Create each table

        String accountTBCreate = "create table "
                + accountTB + " ("
                + aId + " integer primary key autoincrement, "
                + userName + " text not null, "
                + password + " text not null, "
                + eMail + " text not null, "
                + acId + " integer not null, "
                + " FOREIGN KEY (" + acId + ") REFERENCES " + customerTB + "(" + cId + "));";

        String customerTBCreate = "create table "
                + customerTB + " ("
                + cId + " integer primary key autoincrement, "
                + dOB + " text not null, "
                + fName + " text not null, "
                + lName + " text not null"
                + ");";

        String borrowerTBCreate = "create table "
                + borrowerTB + " ("
                + bId + " integer primary key, "
                + hmExp + " double not null, "
                + rStatus + " text not null, "
                + mNetIncome + " double not null, "
                + mSNetIncome + " double not null, "
                + pStatus + " text not null, "
                + mStatus + " text not null, "
                + " FOREIGN KEY (" + bId + ") REFERENCES " + customerTB + "(" + cId + "));";

        String investorTBCreate = "create table "
                + investorTB + " ("
                + inId + " integer primary key, "
                + sOFunds + " text not null, "
                + pRLevel + " text not null, "
                + " FOREIGN KEY (" + inId + ") REFERENCES " + customerTB + "(" + cId + "));";

        String investmentTBCreate = "create table "
                + investmentTB + " ("
                + iId + " integer primary key autoincrement, "
                + sFee + " double not null, "
                + mEarnings + " double not null, "
                + aDeal + " boolean not null, "
                + aTerms + " boolean not null, "
                + tLAmount + " double not null, "
                + cId + " integer not null, "
                + " FOREIGN KEY (" + cId + ") REFERENCES " + customerTB + "(" + cId + "));";

        String loanTBCreate = "create table "
                + loanTB + " ("
                + lId + " integer primary key autoincrement, "
                + pOBorrowing + " text not null, "
                + oFee + " double not null, "
                + iRate + " double not null, "
                + uPFee + " double not null, "
                + lPFee + " double not null, "
                + mPAmount + " double not null, "
                + aTerms + " boolean not null, "
                + lAmount + " double not null, "
                + pPeriod + " integer not null, "
                + sDOAgreement + " text not null, "
                + cId + " integer not null, "
                + " FOREIGN KEY (" + cId + ") REFERENCES " + customerTB + "(" + cId + "));";


        String transactionTBCreate = "create table "
                + transactionTB + " ("
                + tId + " integer primary key, "
                + tDesc + " text not null unique"
                + ");";

        tableNameArray = new String[]{accountTBCreate, customerTBCreate, borrowerTBCreate, investorTBCreate, investmentTBCreate, loanTBCreate, transactionTBCreate};
        try {
            for (int i = 0; i < tableNameArray.length; i++) {
                db.execSQL(tableNameArray[i]);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.i("Database", "Table(s) successfully created!");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        try {
            for (int i = 0; i < tableNameArray.length; i++) {
                db.execSQL("drop table if exists " + tableNameArray[i]);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        onCreate(db);
        Log.i("Database", "Table(s) successfully dropped!");
    }

    public long insertIntoBorrowersTb(Borrower aBorrower) {
        myDb = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(bId, aBorrower.getbId());
        values.put(hmExp, aBorrower.getHmExp());
        values.put(rStatus, aBorrower.getrStatus());
        values.put(mNetIncome, aBorrower.getmNetIncome());
        values.put(mSNetIncome, aBorrower.getmSNetIncome());
        values.put(mStatus, aBorrower.getmStatus());
        values.put(pStatus, aBorrower.getpStatus());

        try {
            numRecordsInserted = myDb.insert(borrowerTB, null, values);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return numRecordsInserted;
    }

    public long insertIntoLoanTb(Loan aLoan) {
        myDb = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(pOBorrowing, aLoan.getpOBorrowing());
        values.put(oFee, aLoan.getoFee());
        values.put(iRate, aLoan.getiRate());
        values.put(uPFee, aLoan.getuPFee());
        values.put(lPFee, aLoan.getlPFee());
        values.put(mPAmount, aLoan.getmPAmount());
        values.put(aTerms, aLoan.getaTerms());
        values.put(lAmount, aLoan.getlAmount());
        values.put(pPeriod, aLoan.getpPeriod());
        values.put(sDOAgreement, aLoan.getsDOAgreement());
        values.put(cId, aLoan.getcId());

        try {
            numRecordsInserted = myDb.insert(loanTB, null, values);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return numRecordsInserted;
    }

    public long insertIntoAccountTb(Account account) {
        myDb = getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(userName, account.getUsername());
        contentValues.put(password, account.getPassword());
        contentValues.put(eMail, account.getEmail());

        try {
            numRecordsInserted = myDb.insert(accountTB, null, contentValues);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return numRecordsInserted;
    }

    public long insertIntoCustomerTb(Customer customer) {
        myDb = getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(dOB, customer.getdOB());
        contentValues.put(fName, customer.getfName());
        contentValues.put(lName, customer.getlName());

        try {
            numRecordsInserted = myDb.insert(accountTB, null, contentValues);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return numRecordsInserted;
    }

    public Boolean CheckUsername(String username) {
        myDb = getWritableDatabase();
        Cursor cursor = null;
        try{
             cursor = myDb.rawQuery("select * from accountTB where userName = ?", new String[]{username});

        }catch (SQLException err){
            err.printStackTrace();
        }if (cursor.getCount() > 0) { //if user exists
            return true;
        } else {
            return false;
        }
    }

    public Boolean CheckUsernamePassword(String username, String password) {
        myDb = getWritableDatabase();
        Cursor cursor = myDb.rawQuery("select * from accountTB where userName = ? and password = ?", new String[]{username, password});
        if (cursor.getCount() > 0) { //if user exists
            return true;
        } else {
            return false;
        }
    }

    public Boolean UpdatePassword(String username, String password){
        myDb = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("password",password);
        long result = myDb.update(accountTB, values, "userName = ?",new String[]{username});
        if (result == -1) {
            return false;
        }
        else{
            return true;
        }

    }

    public Boolean insertIntoAccountTb2(String user, String email, String pass) {
        myDb = getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(userName, user);
        contentValues.put(eMail, email);
        contentValues.put(password, pass);

        long result = myDb.insert(accountTB, null, contentValues);

        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public Boolean insertIntoCustomerTb2(String dob, String fname, String lname) {
        myDb = getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(dOB, dob);
        contentValues.put(fName, fname);
        contentValues.put(lName, lname);

        long result = myDb.insert(customerTB, null, contentValues);

        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }
}
