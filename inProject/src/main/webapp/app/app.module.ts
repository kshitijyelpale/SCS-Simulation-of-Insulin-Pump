import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import './vendor';
import { InglpumpSharedModule } from 'app/shared/shared.module';
import { InglpumpCoreModule } from 'app/core/core.module';
import { InglpumpAppRoutingModule } from './app-routing.module';
import { InglpumpHomeModule } from './home/home.module';
import { InglpumpEntityModule } from './entities/entity.module';
// jhipster-needle-angular-add-module-import JHipster will add new module here
import { MainComponent } from './layouts/main/main.component';
import { NavbarComponent } from './layouts/navbar/navbar.component';
import { FooterComponent } from './layouts/footer/footer.component';
import { PageRibbonComponent } from './layouts/profiles/page-ribbon.component';
import { ActiveMenuDirective } from './layouts/navbar/active-menu.directive';
import { ErrorComponent } from './layouts/error/error.component';

@NgModule({
  imports: [
    BrowserModule,
    InglpumpSharedModule,
    InglpumpCoreModule,
    InglpumpHomeModule,
    // jhipster-needle-angular-add-module JHipster will add new module here
    InglpumpEntityModule,
    InglpumpAppRoutingModule
  ],
  declarations: [MainComponent, NavbarComponent, ErrorComponent, PageRibbonComponent, ActiveMenuDirective, FooterComponent],
  bootstrap: [MainComponent]
})
export class InglpumpAppModule {}
