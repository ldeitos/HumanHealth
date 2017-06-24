
import { AppModule } from './app.module';
import { LanguageService } from './language/language.service';


export abstract class AbstractComponent {  
  private ls: LanguageService = AppModule.injector.get(LanguageService);

  getLocalizedValue(key: string): string {
        return this.ls.getValue(key);
  }
}