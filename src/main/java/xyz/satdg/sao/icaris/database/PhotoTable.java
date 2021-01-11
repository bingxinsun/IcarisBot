package xyz.satdg.sao.icaris.database;

import xyz.satdg.sao.icaris.api.bases.TableBase;
import xyz.satdg.sao.icaris.base.PhotoStd;
import xyz.satdg.sao.icaris.base.TableStd;
import xyz.satdg.sao.icaris.core.DbSystem;

import java.sql.SQLException;

import static xyz.satdg.sao.icaris.core.IcarisBotSystem.ICARIS_LOGGER;

/**
 * @author GongSunink
 */
public class PhotoTable extends TableBase<PhotoStd> {

    @Override
    public TableStd tableStd() {
        return new TableStd("PhotoTable");
    }

    @Override
    public void initTable() {
        if (DbSystem.isTableExist(this.tableStd().getTableName())) {
            ICARIS_LOGGER.info("Data Table<" + this.tableStd().getTableName() +
                    ">Load Successful");
        } else {
            try {
                ICARIS_LOGGER.info("Data Table<" + this.tableStd().getTableName() +
                        ">dose not Exist,Creating a new");
                DbSystem.getGlobalConnection().createStatement().execute("" +
                        "CREATE TABLE " +
                        "PhotoTable(" +
                        "ID INT  NOT NULL," +
                        "Url  TEXT  NOT NULL," +
                        "base64Code blob NOT NULL," +
                        "FromGroupName TEXT," +
                        "FromGroupId INTEGER," +
                        "Author TEXT NOT NULL)");
                ICARIS_LOGGER.info("Data Table<" + this.tableStd().getTableName() +
                        ">Create Successful");
            } catch (SQLException e) {
                ICARIS_LOGGER.error("Data Table<" + this.tableStd().getTableName() +
                        ">Create failed", e);
            }
        }
    }

    @Override
    public void insert(PhotoStd object) {

    }

    @Override
    public PhotoStd select(PhotoStd object) {
        return null;
    }

    @Override
    public boolean update(PhotoStd object) {
        return false;
    }
}
