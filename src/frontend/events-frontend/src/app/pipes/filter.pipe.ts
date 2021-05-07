import { Pipe, PipeTransform } from '@angular/core';
import {Events} from '../model/events.module';

@Pipe({
  name: 'filter'
})
export class FilterPipe implements PipeTransform {

  transform(events: Events[], searchText: string): any[] {
    if (!events){
      return [];
    }

    if(!searchText){
      return events;
    }

    searchText = searchText.toLocaleLowerCase();

    return events.filter(it => {
      return it.title.toLocaleLowerCase().includes(searchText);
    });
  }

}
