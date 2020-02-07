import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { InglpumpSharedModule } from 'app/shared/shared.module';
import { HOME_ROUTE } from './home.route';
import { HomeComponent } from './home.component';
import { ChartsModule } from 'ng2-charts';
import { MatTabsModule } from '@angular/material';
import {MatCardModule} from '@angular/material/card';
import {MatBottomSheetModule} from '@angular/material/bottom-sheet';

@NgModule({
  imports: [BrowserAnimationsModule,MatTabsModule, MatCardModule,MatBottomSheetModule, InglpumpSharedModule, ChartsModule, RouterModule.forChild([HOME_ROUTE])],
  declarations: [HomeComponent]
})
export class InglpumpHomeModule {}
