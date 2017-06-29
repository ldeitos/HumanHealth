
import { DomSanitizer, SafeHtml } from '@angular/platform-browser';

import { AppModule } from './app.module';
import { LanguageService } from './language/language.service';


export abstract class AbstractComponent {  
  private ls: LanguageService = AppModule.injector.get(LanguageService);
  private sanitizer: DomSanitizer = AppModule.injector.get(DomSanitizer);

  getLocalizedValue(key: string): string {
        return this.ls.getValue(key);
  }

  getLocalizedValueAsHtml(key: string): SafeHtml {
        return this.sanitizer.bypassSecurityTrustHtml(this.getLocalizedValue(key));
  }
}