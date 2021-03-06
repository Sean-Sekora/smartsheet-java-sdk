/*
 * #[license]
 * Smartsheet Java SDK
 * %%
 * Copyright (C) 2014 - 2015 Smartsheet
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * %[license]
 */
import com.smartsheet.api.Smartsheet;
import com.smartsheet.api.SmartsheetException;
import com.smartsheet.api.models.*;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;

import static org.junit.Assert.assertNotNull;

public class CellResourcesIT extends ITResourcesImpl{
    Smartsheet smartsheet;

    @Before
    public void setUp() throws Exception {
        smartsheet = createAuthentication();
    }

    @Test
    public void testGetCellHistory() throws SmartsheetException, IOException {
        //create sheet
        Sheet sheet = smartsheet.sheetResources().createSheet(createSheetObject());
        PaginationParameters parameters = new PaginationParameters.PaginationParametersBuilder().setIncludeAll(true).build();

        //get columns
        PagedResult<Column> columns = smartsheet.sheetResources().columnResources().listColumns(sheet.getId(),null,parameters);
        //add rows
        Row row = addRows(sheet.getId());

        PagedResult<CellHistory> cellHistory= smartsheet.sheetResources().rowResources().cellResources().getCellHistory(sheet.getId(), row.getId() ,columns.getData().get(0).getId(), parameters);
        assertNotNull(cellHistory);

        //cleanup
        deleteSheet(sheet.getId());
    }

    @Test
    public void testAddCellImage() throws SmartsheetException, IOException {
        Sheet sheet = smartsheet.sheetResources().createSheet(createSheetObject());
        PaginationParameters parameters = new PaginationParameters.PaginationParametersBuilder().setIncludeAll(true).build();

        //get columns
        PagedResult<Column> columns = smartsheet.sheetResources().columnResources().listColumns(sheet.getId(),null,parameters);
        //add rows
        Row row = addRows(sheet.getId());

        smartsheet.sheetResources().rowResources().cellResources().addImageToCell(sheet.getId(), row.getId(),
                columns.getData().get(0).getId(), "src/integration-test/resources/exclam.png", "image/png", false, "alt text");

        File file = new File("src/integration-test/resources/exclam.png");
        smartsheet.sheetResources().rowResources().cellResources().addImageToCell(sheet.getId(), row.getId(),
                columns.getData().get(0).getId(), file, "image/png", false, file.getName());

        InputStream is = new FileInputStream(file);
        smartsheet.sheetResources().rowResources().cellResources().addImageToCell(sheet.getId(), row.getId(),
                columns.getData().get(0).getId(), is, "image/png", file.length(), true, file.getName());

        //cleanup
        deleteSheet(sheet.getId());
    }
}
