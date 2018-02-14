package com.mongoDB.vacation.sample;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@Document
public class DefaultDashboardSettings
{
 private static final String DEFAULT_DASHBOARD ="{id:333, description:[{cols: 2, rows: 1, y: 0, x: 0},{cols: 2, rows: 2, y: 0, x: 2, hasContent: true},{cols: 1, rows: 1, y: 0, x: 4},{cols: 1, rows: 1, y: 2, x: 5},{cols: undefined, rows: undefined, y: 1, x: 0},{cols: 1, rows: 1, y: undefined, x: undefined},{cols: 2, rows: 2, y: 3, x: 5, minItemRows: 2, minItemCols: 2, label: 'Min rows & cols = 2'},   {cols: 2, rows: 2, y: 2, x: 0, maxItemRows: 2, maxItemCols: 2, label: 'Max rows & cols = 2'},{cols: 2, rows: 1, y: 2, x: 2, dragEnabled: true, resizeEnabled: true, label: 'Drag&Resize Enabled'}, {cols: 1, rows: 1, y: 2, x: 4, dragEnabled: false, resizeEnabled: false, label: 'Drag&Resize Disabled'},{cols: 1, rows: 1, y: 2, x: 6, initCallback: GridComponent.itemInit}]}";

    public String getDEFAULT_DASHBOARD() {
        return DEFAULT_DASHBOARD;
    }
}
