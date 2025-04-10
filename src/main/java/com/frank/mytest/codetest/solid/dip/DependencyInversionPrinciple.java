package com.frank.mytest.codetest.solid.dip;

public class DependencyInversionPrinciple {

    class Application {
        private Database database;

        public Application(DatabaseFactory databaseFactory) { // 注入介面
            this.database = databaseFactory.makeDb();
        }
    }

    interface DatabaseFactory {
        Database makeDb();
    }

    class DatabaseFactoryImpl implements DatabaseFactory{

        @Override
        public Database makeDb() {
            return new MySql();
        }
    }

    interface Database {
        String getHost();
        String getPort();
        String getUserName();
        String getPassword();
    }

    class MySql implements Database {
        @Override
        public String getHost() {
            return null;
        }

        @Override
        public String getPort() {
            return null;
        }

        @Override
        public String getUserName() {
            return null;
        }

        @Override
        public String getPassword() {
            return null;
        }
    }
}
