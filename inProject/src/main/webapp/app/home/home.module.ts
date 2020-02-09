import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { InglpumpSharedModule } from 'app/shared/shared.module';
import { HOME_ROUTE } from './home.route';
import { HomeComponent } from './home.component';
import { AlertComponent} from './home.component';
import { ChartsModule } from 'ng2-charts';
import { MatTabsModule } from '@angular/material';
import {MatCardModule} from '@angular/material/card';
import {MatSnackBarModule} from '@angular/material/snack-bar';
@NgModule({
  imports: [BrowserAnimationsModule,MatTabsModule, MatCardModule,MatSnackBarModule, InglpumpSharedModule, ChartsModule, RouterModule.forChild([HOME_ROUTE])],
  declarations: [HomeComponent, AlertComponent],
  entryComponents: [AlertComponent]
})
export class InglpumpHomeModule {}
