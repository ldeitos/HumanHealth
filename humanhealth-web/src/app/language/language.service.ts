import { Headers, Http } from '@angular/http';
import { Injectable } from '@angular/core';

import 'rxjs/add/operator/toPromise';
import * as content_ptBR from './pt-BR/content.json';


const CONTENT_READERS: Map<string, any> = new Map<string, any>();
export const DEFAULT_LANGUAGE: string = "pt-BR";
export const SUPPORTED_LANGUAGES: string[] = [DEFAULT_LANGUAGE];

CONTENT_READERS.set(DEFAULT_LANGUAGE, content_ptBR);

@Injectable()
export class LanguageService {

    constructor(private http: Http) { }

    getValue(key: string): string {
        var reader = CONTENT_READERS.get(this.getActiveLanguage());
        var command = "reader." + key;        
        var value: string = eval(command);    
 
        return value;
    }

    getActiveLanguage(): string {
        var activeLanguage: string = DEFAULT_LANGUAGE;
        // TODO: lógica para obter lingua preferencial do usuário logado.
        return activeLanguage;
    }
}