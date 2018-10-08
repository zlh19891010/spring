/**
 * 定义sdui基本工具类
 * 
 * @author wangbinbin
 * @date 2016/11/28
 * 
 */
var sdutil = {
	trim:function (str) {
        return str.replace(/(^[ \t\n\r]+)|([ \t\n\r]+$)/g, '');
    }
	,removeItem:function (array, item) {
        for (var i = 0,len=array.length; i < len; i++) {
        	
            if (_.isEqual(array[i],item)) {
                array.splice(i, 1);
                i--;
            }
        }
        
        return array;
    }
	,defer:function (fn, delay, exclusion) {
        var timerID;
        return (function () {
        	
            if (exclusion) {
                clearTimeout(timerID);
            }
            
            timerID = setTimeout(fn, delay);
            return timerID;
        })();
       
    }
    ,isFunction:function(obj){
    	return _.isFunction(obj);
    }
    ,isArray:function(obj){
    	return _.isArray(obj);
    }
    ,isEqual:function(a,b){
    	return _.isEqual(a,b);
    }
    ,isEmpty:function(obj) {
    	return _.isEmpty(obj);
    }
};

/**
 * 扩展数组的删除操作
 * 
 * @author wangbinbin
 */
Array.prototype.del=function(index){
    if(isNaN(index)||index>=this.length){
        return false;
    }
    for(var i=0,n=0;i<this.length;i++){
        if(this[i]!=this[index]){
            this[n++]=this[i];
        }
    }
    this.length-=1;
};

/**
 * 得到指定元素的下标
 * 
 * @author wangbinbin
 */
Array.prototype.indexOf = function(val) {
    for (var i = 0; i < this.length; i++) {
        if (sdutil.isEqual(this[i],val)) return i;
    }
    return -1;
};

/**
 * 移除指定元素
 * 
 * @author wangbinbin
 */
Array.prototype.remove = function(val) {
    var index = this.indexOf(val);
    if (index > -1) {
        this.splice(index, 1);
    }
};

/**
 * 扩展日期的格式化操作
 * @param fmt 比如：  yyyy-MM-dd
 * @returns
 * 
 * @author wangbinbin
 */
Date.prototype.format = function (fmt) {
    var o = {
        "M+": this.getMonth() + 1,
        "d+": this.getDate(),
        "h+": this.getHours(), 
        "m+": this.getMinutes(),
        "s+": this.getSeconds(),
        "q+": Math.floor((this.getMonth() + 3) / 3),
        "S": this.getMilliseconds()
    };
    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
    if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
};


(function() {

	  var root = typeof self == 'object' && self.self === self && self ||
	            typeof global == 'object' && global.global === global && global ||
	            this;

	  var previousUnderscore = root._;

	  var ArrayProto = Array.prototype, ObjProto = Object.prototype;
	  var SymbolProto = typeof Symbol !== 'undefined' ? Symbol.prototype : null;

	  var push = ArrayProto.push,
	      slice = ArrayProto.slice,
	      toString = ObjProto.toString,
	      hasOwnProperty = ObjProto.hasOwnProperty;

	  var nativeIsArray = Array.isArray,
	      nativeKeys = Object.keys,
	      nativeCreate = Object.create;

	  var Ctor = function(){};

	  var _ = function(obj) {
	    if (obj instanceof _) return obj;
	    if (!(this instanceof _)) return new _(obj);
	    this._wrapped = obj;
	  };

	  if (typeof exports != 'undefined' && !exports.nodeType) {
	    if (typeof module != 'undefined' && !module.nodeType && module.exports) {
	      exports = module.exports = _;
	    }
	    exports._ = _;
	  } else {
	    root._ = _;
	  }

	  _.VERSION = '1.8.3';

	  var optimizeCb = function(func, context, argCount) {
	    if (context === void 0) return func;
	    switch (argCount) {
	      case 1: return function(value) {
	        return func.call(context, value);
	      };
	      case null:
	      case 3: return function(value, index, collection) {
	        return func.call(context, value, index, collection);
	      };
	      case 4: return function(accumulator, value, index, collection) {
	        return func.call(context, accumulator, value, index, collection);
	      };
	    }
	    return function() {
	      return func.apply(context, arguments);
	    };
	  };

	  var builtinIteratee;

	  var cb = function(value, context, argCount) {
	    if (_.iteratee !== builtinIteratee) return _.iteratee(value, context);
	    if (value == null) return _.identity;
	    if (_.isFunction(value)) return optimizeCb(value, context, argCount);
	    if (_.isObject(value) && !_.isArray(value)) return _.matcher(value);
	    return _.property(value);
	  };

	  _.iteratee = builtinIteratee = function(value, context) {
	    return cb(value, context, Infinity);
	  };

	  var restArgs = function(func, startIndex) {
	    startIndex = startIndex == null ? func.length - 1 : +startIndex;
	    return function() {
	      var length = Math.max(arguments.length - startIndex, 0),
	          rest = Array(length),
	          index = 0;
	      for (; index < length; index++) {
	        rest[index] = arguments[index + startIndex];
	      }
	      switch (startIndex) {
	        case 0: return func.call(this, rest);
	        case 1: return func.call(this, arguments[0], rest);
	        case 2: return func.call(this, arguments[0], arguments[1], rest);
	      }
	      var args = Array(startIndex + 1);
	      for (index = 0; index < startIndex; index++) {
	        args[index] = arguments[index];
	      }
	      args[startIndex] = rest;
	      return func.apply(this, args);
	    };
	  };

	  var baseCreate = function(prototype) {
	    if (!_.isObject(prototype)) return {};
	    if (nativeCreate) return nativeCreate(prototype);
	    Ctor.prototype = prototype;
	    var result = new Ctor;
	    Ctor.prototype = null;
	    return result;
	  };

	  var shallowProperty = function(key) {
	    return function(obj) {
	      return obj == null ? void 0 : obj[key];
	    };
	  };

	  var deepGet = function(obj, path) {
	    var length = path.length;
	    for (var i = 0; i < length; i++) {
	      if (obj == null) return void 0;
	      obj = obj[path[i]];
	    }
	    return length ? obj : void 0;
	  };

	  var MAX_ARRAY_INDEX = Math.pow(2, 53) - 1;
	  var getLength = shallowProperty('length');
	  var isArrayLike = function(collection) {
	    var length = getLength(collection);
	    return typeof length == 'number' && length >= 0 && length <= MAX_ARRAY_INDEX;
	  };

	  _.each = _.forEach = function(obj, iteratee, context) {
	    iteratee = optimizeCb(iteratee, context);
	    var i, length;
	    if (isArrayLike(obj)) {
	      for (i = 0, length = obj.length; i < length; i++) {
	        iteratee(obj[i], i, obj);
	      }
	    } else {
	      var keys = _.keys(obj);
	      for (i = 0, length = keys.length; i < length; i++) {
	        iteratee(obj[keys[i]], keys[i], obj);
	      }
	    }
	    return obj;
	  };

	  _.map = _.collect = function(obj, iteratee, context) {
	    iteratee = cb(iteratee, context);
	    var keys = !isArrayLike(obj) && _.keys(obj),
	        length = (keys || obj).length,
	        results = Array(length);
	    for (var index = 0; index < length; index++) {
	      var currentKey = keys ? keys[index] : index;
	      results[index] = iteratee(obj[currentKey], currentKey, obj);
	    }
	    return results;
	  };

	  var createReduce = function(dir) {
	    var reducer = function(obj, iteratee, memo, initial) {
	      var keys = !isArrayLike(obj) && _.keys(obj),
	          length = (keys || obj).length,
	          index = dir > 0 ? 0 : length - 1;
	      if (!initial) {
	        memo = obj[keys ? keys[index] : index];
	        index += dir;
	      }
	      for (; index >= 0 && index < length; index += dir) {
	        var currentKey = keys ? keys[index] : index;
	        memo = iteratee(memo, obj[currentKey], currentKey, obj);
	      }
	      return memo;
	    };

	    return function(obj, iteratee, memo, context) {
	      var initial = arguments.length >= 3;
	      return reducer(obj, optimizeCb(iteratee, context, 4), memo, initial);
	    };
	  };

	  _.reduce = _.foldl = _.inject = createReduce(1);

	  _.reduceRight = _.foldr = createReduce(-1);

	  _.find = _.detect = function(obj, predicate, context) {
	    var keyFinder = isArrayLike(obj) ? _.findIndex : _.findKey;
	    var key = keyFinder(obj, predicate, context);
	    if (key !== void 0 && key !== -1) return obj[key];
	  };

	  _.filter = _.select = function(obj, predicate, context) {
	    var results = [];
	    predicate = cb(predicate, context);
	    _.each(obj, function(value, index, list) {
	      if (predicate(value, index, list)) results.push(value);
	    });
	    return results;
	  };

	  _.reject = function(obj, predicate, context) {
	    return _.filter(obj, _.negate(cb(predicate)), context);
	  };

	  _.every = _.all = function(obj, predicate, context) {
	    predicate = cb(predicate, context);
	    var keys = !isArrayLike(obj) && _.keys(obj),
	        length = (keys || obj).length;
	    for (var index = 0; index < length; index++) {
	      var currentKey = keys ? keys[index] : index;
	      if (!predicate(obj[currentKey], currentKey, obj)) return false;
	    }
	    return true;
	  };

	  _.some = _.any = function(obj, predicate, context) {
	    predicate = cb(predicate, context);
	    var keys = !isArrayLike(obj) && _.keys(obj),
	        length = (keys || obj).length;
	    for (var index = 0; index < length; index++) {
	      var currentKey = keys ? keys[index] : index;
	      if (predicate(obj[currentKey], currentKey, obj)) return true;
	    }
	    return false;
	  };

	  _.contains = _.includes = _.include = function(obj, item, fromIndex, guard) {
	    if (!isArrayLike(obj)) obj = _.values(obj);
	    if (typeof fromIndex != 'number' || guard) fromIndex = 0;
	    return _.indexOf(obj, item, fromIndex) >= 0;
	  };

	  _.invoke = restArgs(function(obj, path, args) {
	    var contextPath, func;
	    if (_.isFunction(path)) {
	      func = path;
	    } else if (_.isArray(path)) {
	      contextPath = path.slice(0, -1);
	      path = path[path.length - 1];
	    }
	    return _.map(obj, function(context) {
	      var method = func;
	      if (!method) {
	        if (contextPath && contextPath.length) {
	          context = deepGet(context, contextPath);
	        }
	        if (context == null) return void 0;
	        method = context[path];
	      }
	      return method == null ? method : method.apply(context, args);
	    });
	  });

	  _.pluck = function(obj, key) {
	    return _.map(obj, _.property(key));
	  };

	  _.where = function(obj, attrs) {
	    return _.filter(obj, _.matcher(attrs));
	  };

	  _.findWhere = function(obj, attrs) {
	    return _.find(obj, _.matcher(attrs));
	  };

	  _.max = function(obj, iteratee, context) {
	    var result = -Infinity, lastComputed = -Infinity,
	        value, computed;
	    if (iteratee == null || (typeof iteratee == 'number' && typeof obj[0] != 'object') && obj != null) {
	      obj = isArrayLike(obj) ? obj : _.values(obj);
	      for (var i = 0, length = obj.length; i < length; i++) {
	        value = obj[i];
	        if (value != null && value > result) {
	          result = value;
	        }
	      }
	    } else {
	      iteratee = cb(iteratee, context);
	      _.each(obj, function(v, index, list) {
	        computed = iteratee(v, index, list);
	        if (computed > lastComputed || computed === -Infinity && result === -Infinity) {
	          result = v;
	          lastComputed = computed;
	        }
	      });
	    }
	    return result;
	  };

	  _.min = function(obj, iteratee, context) {
	    var result = Infinity, lastComputed = Infinity,
	        value, computed;
	    if (iteratee == null || (typeof iteratee == 'number' && typeof obj[0] != 'object') && obj != null) {
	      obj = isArrayLike(obj) ? obj : _.values(obj);
	      for (var i = 0, length = obj.length; i < length; i++) {
	        value = obj[i];
	        if (value != null && value < result) {
	          result = value;
	        }
	      }
	    } else {
	      iteratee = cb(iteratee, context);
	      _.each(obj, function(v, index, list) {
	        computed = iteratee(v, index, list);
	        if (computed < lastComputed || computed === Infinity && result === Infinity) {
	          result = v;
	          lastComputed = computed;
	        }
	      });
	    }
	    return result;
	  };

	  _.shuffle = function(obj) {
	    return _.sample(obj, Infinity);
	  };

	  _.sample = function(obj, n, guard) {
	    if (n == null || guard) {
	      if (!isArrayLike(obj)) obj = _.values(obj);
	      return obj[_.random(obj.length - 1)];
	    }
	    var sample = isArrayLike(obj) ? _.clone(obj) : _.values(obj);
	    var length = getLength(sample);
	    n = Math.max(Math.min(n, length), 0);
	    var last = length - 1;
	    for (var index = 0; index < n; index++) {
	      var rand = _.random(index, last);
	      var temp = sample[index];
	      sample[index] = sample[rand];
	      sample[rand] = temp;
	    }
	    return sample.slice(0, n);
	  };

	  _.sortBy = function(obj, iteratee, context) {
	    var index = 0;
	    iteratee = cb(iteratee, context);
	    return _.pluck(_.map(obj, function(value, key, list) {
	      return {
	        value: value,
	        index: index++,
	        criteria: iteratee(value, key, list)
	      };
	    }).sort(function(left, right) {
	      var a = left.criteria;
	      var b = right.criteria;
	      if (a !== b) {
	        if (a > b || a === void 0) return 1;
	        if (a < b || b === void 0) return -1;
	      }
	      return left.index - right.index;
	    }), 'value');
	  };

	  var group = function(behavior, partition) {
	    return function(obj, iteratee, context) {
	      var result = partition ? [[], []] : {};
	      iteratee = cb(iteratee, context);
	      _.each(obj, function(value, index) {
	        var key = iteratee(value, index, obj);
	        behavior(result, value, key);
	      });
	      return result;
	    };
	  };

	  _.groupBy = group(function(result, value, key) {
	    if (_.has(result, key)) result[key].push(value); else result[key] = [value];
	  });

	  _.indexBy = group(function(result, value, key) {
	    result[key] = value;
	  });

	  _.countBy = group(function(result, value, key) {
	    if (_.has(result, key)) result[key]++; else result[key] = 1;
	  });

	  var reStrSymbol = /[^\ud800-\udfff]|[\ud800-\udbff][\udc00-\udfff]|[\ud800-\udfff]/g;
	  _.toArray = function(obj) {
	    if (!obj) return [];
	    if (_.isArray(obj)) return slice.call(obj);
	    if (_.isString(obj)) {
	      return obj.match(reStrSymbol);
	    }
	    if (isArrayLike(obj)) return _.map(obj, _.identity);
	    return _.values(obj);
	  };

	  _.size = function(obj) {
	    if (obj == null) return 0;
	    return isArrayLike(obj) ? obj.length : _.keys(obj).length;
	  };

	  _.partition = group(function(result, value, pass) {
	    result[pass ? 0 : 1].push(value);
	  }, true);

	  _.first = _.head = _.take = function(array, n, guard) {
	    if (array == null || array.length < 1) return void 0;
	    if (n == null || guard) return array[0];
	    return _.initial(array, array.length - n);
	  };

	  _.initial = function(array, n, guard) {
	    return slice.call(array, 0, Math.max(0, array.length - (n == null || guard ? 1 : n)));
	  };

	  _.last = function(array, n, guard) {
	    if (array == null || array.length < 1) return void 0;
	    if (n == null || guard) return array[array.length - 1];
	    return _.rest(array, Math.max(0, array.length - n));
	  };

	  _.rest = _.tail = _.drop = function(array, n, guard) {
	    return slice.call(array, n == null || guard ? 1 : n);
	  };

	  _.compact = function(array) {
	    return _.filter(array, Boolean);
	  };

	  var flatten = function(input, shallow, strict, output) {
	    output = output || [];
	    var idx = output.length;
	    for (var i = 0, length = getLength(input); i < length; i++) {
	      var value = input[i];
	      if (isArrayLike(value) && (_.isArray(value) || _.isArguments(value))) {
	        if (shallow) {
	          var j = 0, len = value.length;
	          while (j < len) output[idx++] = value[j++];
	        } else {
	          flatten(value, shallow, strict, output);
	          idx = output.length;
	        }
	      } else if (!strict) {
	        output[idx++] = value;
	      }
	    }
	    return output;
	  };

	  _.flatten = function(array, shallow) {
	    return flatten(array, shallow, false);
	  };

	  _.without = restArgs(function(array, otherArrays) {
	    return _.difference(array, otherArrays);
	  });

	  _.uniq = _.unique = function(array, isSorted, iteratee, context) {
	    if (!_.isBoolean(isSorted)) {
	      context = iteratee;
	      iteratee = isSorted;
	      isSorted = false;
	    }
	    if (iteratee != null) iteratee = cb(iteratee, context);
	    var result = [];
	    var seen = [];
	    for (var i = 0, length = getLength(array); i < length; i++) {
	      var value = array[i],
	          computed = iteratee ? iteratee(value, i, array) : value;
	      if (isSorted) {
	        if (!i || seen !== computed) result.push(value);
	        seen = computed;
	      } else if (iteratee) {
	        if (!_.contains(seen, computed)) {
	          seen.push(computed);
	          result.push(value);
	        }
	      } else if (!_.contains(result, value)) {
	        result.push(value);
	      }
	    }
	    return result;
	  };

	  _.union = restArgs(function(arrays) {
	    return _.uniq(flatten(arrays, true, true));
	  });

	  _.intersection = function(array) {
	    var result = [];
	    var argsLength = arguments.length;
	    for (var i = 0, length = getLength(array); i < length; i++) {
	      var item = array[i];
	      if (_.contains(result, item)) continue;
	      var j;
	      for (j = 1; j < argsLength; j++) {
	        if (!_.contains(arguments[j], item)) break;
	      }
	      if (j === argsLength) result.push(item);
	    }
	    return result;
	  };

	  _.difference = restArgs(function(array, rest) {
	    rest = flatten(rest, true, true);
	    return _.filter(array, function(value){
	      return !_.contains(rest, value);
	    });
	  });

	  _.unzip = function(array) {
	    var length = array && _.max(array, getLength).length || 0;
	    var result = Array(length);

	    for (var index = 0; index < length; index++) {
	      result[index] = _.pluck(array, index);
	    }
	    return result;
	  };

	  _.zip = restArgs(_.unzip);

	  _.object = function(list, values) {
	    var result = {};
	    for (var i = 0, length = getLength(list); i < length; i++) {
	      if (values) {
	        result[list[i]] = values[i];
	      } else {
	        result[list[i][0]] = list[i][1];
	      }
	    }
	    return result;
	  };
	  var createPredicateIndexFinder = function(dir) {
	    return function(array, predicate, context) {
	      predicate = cb(predicate, context);
	      var length = getLength(array);
	      var index = dir > 0 ? 0 : length - 1;
	      for (; index >= 0 && index < length; index += dir) {
	        if (predicate(array[index], index, array)) return index;
	      }
	      return -1;
	    };
	  };

	  _.findIndex = createPredicateIndexFinder(1);
	  _.findLastIndex = createPredicateIndexFinder(-1);
	  _.sortedIndex = function(array, obj, iteratee, context) {
	    iteratee = cb(iteratee, context, 1);
	    var value = iteratee(obj);
	    var low = 0, high = getLength(array);
	    while (low < high) {
	      var mid = Math.floor((low + high) / 2);
	      if (iteratee(array[mid]) < value) low = mid + 1; else high = mid;
	    }
	    return low;
	  };
	  var createIndexFinder = function(dir, predicateFind, sortedIndex) {
	    return function(array, item, idx) {
	      var i = 0, length = getLength(array);
	      if (typeof idx == 'number') {
	        if (dir > 0) {
	          i = idx >= 0 ? idx : Math.max(idx + length, i);
	        } else {
	          length = idx >= 0 ? Math.min(idx + 1, length) : idx + length + 1;
	        }
	      } else if (sortedIndex && idx && length) {
	        idx = sortedIndex(array, item);
	        return array[idx] === item ? idx : -1;
	      }
	      if (item !== item) {
	        idx = predicateFind(slice.call(array, i, length), _.isNaN);
	        return idx >= 0 ? idx + i : -1;
	      }
	      for (idx = dir > 0 ? i : length - 1; idx >= 0 && idx < length; idx += dir) {
	        if (array[idx] === item) return idx;
	      }
	      return -1;
	    };
	  };
	  _.indexOf = createIndexFinder(1, _.findIndex, _.sortedIndex);
	  _.lastIndexOf = createIndexFinder(-1, _.findLastIndex);
	  _.range = function(start, stop, step) {
	    if (stop == null) {
	      stop = start || 0;
	      start = 0;
	    }
	    if (!step) {
	      step = stop < start ? -1 : 1;
	    }

	    var length = Math.max(Math.ceil((stop - start) / step), 0);
	    var range = Array(length);

	    for (var idx = 0; idx < length; idx++, start += step) {
	      range[idx] = start;
	    }

	    return range;
	  };

	  _.chunk = function(array, count) {
	    if (count == null || count < 1) return [];

	    var result = [];
	    var i = 0, length = array.length;
	    while (i < length) {
	      result.push(slice.call(array, i, i += count));
	    }
	    return result;
	  };

	  var executeBound = function(sourceFunc, boundFunc, context, callingContext, args) {
	    if (!(callingContext instanceof boundFunc)) return sourceFunc.apply(context, args);
	    var self = baseCreate(sourceFunc.prototype);
	    var result = sourceFunc.apply(self, args);
	    if (_.isObject(result)) return result;
	    return self;
	  };

	  _.bind = restArgs(function(func, context, args) {
	    if (!_.isFunction(func)) throw new TypeError('Bind must be called on a function');
	    var bound = restArgs(function(callArgs) {
	      return executeBound(func, bound, context, this, args.concat(callArgs));
	    });
	    return bound;
	  });

	  _.partial = restArgs(function(func, boundArgs) {
	    var placeholder = _.partial.placeholder;
	    var bound = function() {
	      var position = 0, length = boundArgs.length;
	      var args = Array(length);
	      for (var i = 0; i < length; i++) {
	        args[i] = boundArgs[i] === placeholder ? arguments[position++] : boundArgs[i];
	      }
	      while (position < arguments.length) args.push(arguments[position++]);
	      return executeBound(func, bound, this, this, args);
	    };
	    return bound;
	  });

	  _.partial.placeholder = _;

	  _.bindAll = restArgs(function(obj, keys) {
	    keys = flatten(keys, false, false);
	    var index = keys.length;
	    if (index < 1) throw new Error('bindAll must be passed function names');
	    while (index--) {
	      var key = keys[index];
	      obj[key] = _.bind(obj[key], obj);
	    }
	  });

	  _.memoize = function(func, hasher) {
	    var memoize = function(key) {
	      var cache = memoize.cache;
	      var address = '' + (hasher ? hasher.apply(this, arguments) : key);
	      if (!_.has(cache, address)) cache[address] = func.apply(this, arguments);
	      return cache[address];
	    };
	    memoize.cache = {};
	    return memoize;
	  };

	  _.delay = restArgs(function(func, wait, args) {
	    return setTimeout(function() {
	      return func.apply(null, args);
	    }, wait);
	  });

	  _.defer = _.partial(_.delay, _, 1);

	  _.throttle = function(func, wait, options) {
	    var timeout, context, args, result;
	    var previous = 0;
	    if (!options) options = {};

	    var later = function() {
	      previous = options.leading === false ? 0 : _.now();
	      timeout = null;
	      result = func.apply(context, args);
	      if (!timeout) context = args = null;
	    };

	    var throttled = function() {
	      var now = _.now();
	      if (!previous && options.leading === false) previous = now;
	      var remaining = wait - (now - previous);
	      context = this;
	      args = arguments;
	      if (remaining <= 0 || remaining > wait) {
	        if (timeout) {
	          clearTimeout(timeout);
	          timeout = null;
	        }
	        previous = now;
	        result = func.apply(context, args);
	        if (!timeout) context = args = null;
	      } else if (!timeout && options.trailing !== false) {
	        timeout = setTimeout(later, remaining);
	      }
	      return result;
	    };

	    throttled.cancel = function() {
	      clearTimeout(timeout);
	      previous = 0;
	      timeout = context = args = null;
	    };

	    return throttled;
	  };

	  _.debounce = function(func, wait, immediate) {
	    var timeout, result;

	    var later = function(context, args) {
	      timeout = null;
	      if (args) result = func.apply(context, args);
	    };

	    var debounced = restArgs(function(args) {
	      if (timeout) clearTimeout(timeout);
	      if (immediate) {
	        var callNow = !timeout;
	        timeout = setTimeout(later, wait);
	        if (callNow) result = func.apply(this, args);
	      } else {
	        timeout = _.delay(later, wait, this, args);
	      }

	      return result;
	    });

	    debounced.cancel = function() {
	      clearTimeout(timeout);
	      timeout = null;
	    };

	    return debounced;
	  };

	  _.wrap = function(func, wrapper) {
	    return _.partial(wrapper, func);
	  };

	  _.negate = function(predicate) {
	    return function() {
	      return !predicate.apply(this, arguments);
	    };
	  };

	  _.compose = function() {
	    var args = arguments;
	    var start = args.length - 1;
	    return function() {
	      var i = start;
	      var result = args[start].apply(this, arguments);
	      while (i--) result = args[i].call(this, result);
	      return result;
	    };
	  };

	  _.after = function(times, func) {
	    return function() {
	      if (--times < 1) {
	        return func.apply(this, arguments);
	      }
	    };
	  };

	  _.before = function(times, func) {
	    var memo;
	    return function() {
	      if (--times > 0) {
	        memo = func.apply(this, arguments);
	      }
	      if (times <= 1) func = null;
	      return memo;
	    };
	  };

	  _.once = _.partial(_.before, 2);

	  _.restArgs = restArgs;

	  var hasEnumBug = !{toString: null}.propertyIsEnumerable('toString');
	  var nonEnumerableProps = ['valueOf', 'isPrototypeOf', 'toString',
	                      'propertyIsEnumerable', 'hasOwnProperty', 'toLocaleString'];

	  var collectNonEnumProps = function(obj, keys) {
	    var nonEnumIdx = nonEnumerableProps.length;
	    var constructor = obj.constructor;
	    var proto = _.isFunction(constructor) && constructor.prototype || ObjProto;

	    var prop = 'constructor';
	    if (_.has(obj, prop) && !_.contains(keys, prop)) keys.push(prop);

	    while (nonEnumIdx--) {
	      prop = nonEnumerableProps[nonEnumIdx];
	      if (prop in obj && obj[prop] !== proto[prop] && !_.contains(keys, prop)) {
	        keys.push(prop);
	      }
	    }
	  };

	  _.keys = function(obj) {
	    if (!_.isObject(obj)) return [];
	    if (nativeKeys) return nativeKeys(obj);
	    var keys = [];
	    for (var key in obj) if (_.has(obj, key)) keys.push(key);
	    if (hasEnumBug) collectNonEnumProps(obj, keys);
	    return keys;
	  };

	  _.allKeys = function(obj) {
	    if (!_.isObject(obj)) return [];
	    var keys = [];
	    for (var key in obj) keys.push(key);
	    if (hasEnumBug) collectNonEnumProps(obj, keys);
	    return keys;
	  };

	  _.values = function(obj) {
	    var keys = _.keys(obj);
	    var length = keys.length;
	    var values = Array(length);
	    for (var i = 0; i < length; i++) {
	      values[i] = obj[keys[i]];
	    }
	    return values;
	  };

	  _.mapObject = function(obj, iteratee, context) {
	    iteratee = cb(iteratee, context);
	    var keys = _.keys(obj),
	        length = keys.length,
	        results = {};
	    for (var index = 0; index < length; index++) {
	      var currentKey = keys[index];
	      results[currentKey] = iteratee(obj[currentKey], currentKey, obj);
	    }
	    return results;
	  };

	  _.pairs = function(obj) {
	    var keys = _.keys(obj);
	    var length = keys.length;
	    var pairs = Array(length);
	    for (var i = 0; i < length; i++) {
	      pairs[i] = [keys[i], obj[keys[i]]];
	    }
	    return pairs;
	  };

	  _.invert = function(obj) {
	    var result = {};
	    var keys = _.keys(obj);
	    for (var i = 0, length = keys.length; i < length; i++) {
	      result[obj[keys[i]]] = keys[i];
	    }
	    return result;
	  };

	  _.functions = _.methods = function(obj) {
	    var names = [];
	    for (var key in obj) {
	      if (_.isFunction(obj[key])) names.push(key);
	    }
	    return names.sort();
	  };

	  var createAssigner = function(keysFunc, defaults) {
	    return function(obj) {
	      var length = arguments.length;
	      if (defaults) obj = Object(obj);
	      if (length < 2 || obj == null) return obj;
	      for (var index = 1; index < length; index++) {
	        var source = arguments[index],
	            keys = keysFunc(source),
	            l = keys.length;
	        for (var i = 0; i < l; i++) {
	          var key = keys[i];
	          if (!defaults || obj[key] === void 0) obj[key] = source[key];
	        }
	      }
	      return obj;
	    };
	  };

	  _.extend = createAssigner(_.allKeys);

	  _.extendOwn = _.assign = createAssigner(_.keys);

	  _.findKey = function(obj, predicate, context) {
	    predicate = cb(predicate, context);
	    var keys = _.keys(obj), key;
	    for (var i = 0, length = keys.length; i < length; i++) {
	      key = keys[i];
	      if (predicate(obj[key], key, obj)) return key;
	    }
	  };

	  var keyInObj = function(value, key, obj) {
	    return key in obj;
	  };

	  _.pick = restArgs(function(obj, keys) {
	    var result = {}, iteratee = keys[0];
	    if (obj == null) return result;
	    if (_.isFunction(iteratee)) {
	      if (keys.length > 1) iteratee = optimizeCb(iteratee, keys[1]);
	      keys = _.allKeys(obj);
	    } else {
	      iteratee = keyInObj;
	      keys = flatten(keys, false, false);
	      obj = Object(obj);
	    }
	    for (var i = 0, length = keys.length; i < length; i++) {
	      var key = keys[i];
	      var value = obj[key];
	      if (iteratee(value, key, obj)) result[key] = value;
	    }
	    return result;
	  });

	  _.omit = restArgs(function(obj, keys) {
	    var iteratee = keys[0], context;
	    if (_.isFunction(iteratee)) {
	      iteratee = _.negate(iteratee);
	      if (keys.length > 1) context = keys[1];
	    } else {
	      keys = _.map(flatten(keys, false, false), String);
	      iteratee = function(value, key) {
	        return !_.contains(keys, key);
	      };
	    }
	    return _.pick(obj, iteratee, context);
	  });

	  _.defaults = createAssigner(_.allKeys, true);

	  _.create = function(prototype, props) {
	    var result = baseCreate(prototype);
	    if (props) _.extendOwn(result, props);
	    return result;
	  };

	  _.clone = function(obj) {
	    if (!_.isObject(obj)) return obj;
	    return _.isArray(obj) ? obj.slice() : _.extend({}, obj);
	  };

	  _.tap = function(obj, interceptor) {
	    interceptor(obj);
	    return obj;
	  };

	  _.isMatch = function(object, attrs) {
	    var keys = _.keys(attrs), length = keys.length;
	    if (object == null) return !length;
	    var obj = Object(object);
	    for (var i = 0; i < length; i++) {
	      var key = keys[i];
	      if (attrs[key] !== obj[key] || !(key in obj)) return false;
	    }
	    return true;
	  };


	  var eq, deepEq;
	  eq = function(a, b, aStack, bStack) {
	    if (a === b) return a !== 0 || 1 / a === 1 / b;
	    if (a == null || b == null) return false;
	    if (a !== a) return b !== b;
	    var type = typeof a;
	    if (type !== 'function' && type !== 'object' && typeof b != 'object') return false;
	    return deepEq(a, b, aStack, bStack);
	  };

	  deepEq = function(a, b, aStack, bStack) {
	    if (a instanceof _) a = a._wrapped;
	    if (b instanceof _) b = b._wrapped;
	    var className = toString.call(a);
	    if (className !== toString.call(b)) return false;
	    switch (className) {
	      case '[object RegExp]':
	      case '[object String]':
	        return '' + a === '' + b;
	      case '[object Number]':
	        if (+a !== +a) return +b !== +b;
	        return +a === 0 ? 1 / +a === 1 / b : +a === +b;
	      case '[object Date]':
	      case '[object Boolean]':
	        return +a === +b;
	      case '[object Symbol]':
	        return SymbolProto.valueOf.call(a) === SymbolProto.valueOf.call(b);
	    }

	    var areArrays = className === '[object Array]';
	    if (!areArrays) {
	      if (typeof a != 'object' || typeof b != 'object') return false;
	      var aCtor = a.constructor, bCtor = b.constructor;
	      if (aCtor !== bCtor && !(_.isFunction(aCtor) && aCtor instanceof aCtor &&
	                               _.isFunction(bCtor) && bCtor instanceof bCtor)
	                          && ('constructor' in a && 'constructor' in b)) {
	        return false;
	      }
	    }
	    aStack = aStack || [];
	    bStack = bStack || [];
	    var length = aStack.length;
	    while (length--) {
	      if (aStack[length] === a) return bStack[length] === b;
	    }

	    aStack.push(a);
	    bStack.push(b);

	    if (areArrays) {
	      length = a.length;
	      if (length !== b.length) return false;
	      while (length--) {
	        if (!eq(a[length], b[length], aStack, bStack)) return false;
	      }
	    } else {
	      var keys = _.keys(a), key;
	      length = keys.length;
	      if (_.keys(b).length !== length) return false;
	      while (length--) {
	        key = keys[length];
	        if (!(_.has(b, key) && eq(a[key], b[key], aStack, bStack))) return false;
	      }
	    }
	    aStack.pop();
	    bStack.pop();
	    return true;
	  };

	  _.isEqual = function(a, b) {
	    return eq(a, b);
	  };

	  _.isEmpty = function(obj) {
	    if (obj == null) return true;
	    if (isArrayLike(obj) && (_.isArray(obj) || _.isString(obj) || _.isArguments(obj))) return obj.length === 0;
	    return _.keys(obj).length === 0;
	  };

	  _.isElement = function(obj) {
	    return !!(obj && obj.nodeType === 1);
	  };

	  _.isArray = nativeIsArray || function(obj) {
	    return toString.call(obj) === '[object Array]';
	  };

	  _.isObject = function(obj) {
	    var type = typeof obj;
	    return type === 'function' || type === 'object' && !!obj;
	  };

	  _.each(['Arguments', 'Function', 'String', 'Number', 'Date', 'RegExp', 'Error', 'Symbol', 'Map', 'WeakMap', 'Set', 'WeakSet'], function(name) {
	    _['is' + name] = function(obj) {
	      return toString.call(obj) === '[object ' + name + ']';
	    };
	  });

	  if (!_.isArguments(arguments)) {
	    _.isArguments = function(obj) {
	      return _.has(obj, 'callee');
	    };
	  }

	  var nodelist = root.document && root.document.childNodes;
	  if (typeof /./ != 'function' && typeof Int8Array != 'object' && typeof nodelist != 'function') {
	    _.isFunction = function(obj) {
	      return typeof obj == 'function' || false;
	    };
	  }

	  _.isFinite = function(obj) {
	    return !_.isSymbol(obj) && isFinite(obj) && !isNaN(parseFloat(obj));
	  };

	  _.isNaN = function(obj) {
	    return _.isNumber(obj) && isNaN(obj);
	  };

	  _.isBoolean = function(obj) {
	    return obj === true || obj === false || toString.call(obj) === '[object Boolean]';
	  };

	  _.isNull = function(obj) {
	    return obj === null;
	  };

	  _.isUndefined = function(obj) {
	    return obj === void 0;
	  };

	  _.has = function(obj, path) {
	    if (!_.isArray(path)) {
	      return obj != null && hasOwnProperty.call(obj, path);
	    }
	    var length = path.length;
	    for (var i = 0; i < length; i++) {
	      var key = path[i];
	      if (obj == null || !hasOwnProperty.call(obj, key)) {
	        return false;
	      }
	      obj = obj[key];
	    }
	    return !!length;
	  };

	  _.noConflict = function() {
	    root._ = previousUnderscore;
	    return this;
	  };

	  _.identity = function(value) {
	    return value;
	  };

	  _.constant = function(value) {
	    return function() {
	      return value;
	    };
	  };

	  _.noop = function(){};

	  _.property = function(path) {
	    if (!_.isArray(path)) {
	      return shallowProperty(path);
	    }
	    return function(obj) {
	      return deepGet(obj, path);
	    };
	  };

	  _.propertyOf = function(obj) {
	    if (obj == null) {
	      return function(){};
	    }
	    return function(path) {
	      return !_.isArray(path) ? obj[path] : deepGet(obj, path);
	    };
	  };

	  _.matcher = _.matches = function(attrs) {
	    attrs = _.extendOwn({}, attrs);
	    return function(obj) {
	      return _.isMatch(obj, attrs);
	    };
	  };

	  _.times = function(n, iteratee, context) {
	    var accum = Array(Math.max(0, n));
	    iteratee = optimizeCb(iteratee, context, 1);
	    for (var i = 0; i < n; i++) accum[i] = iteratee(i);
	    return accum;
	  };

	  _.random = function(min, max) {
	    if (max == null) {
	      max = min;
	      min = 0;
	    }
	    return min + Math.floor(Math.random() * (max - min + 1));
	  };

	  _.now = Date.now || function() {
	    return new Date().getTime();
	  };

	  var escapeMap = {
	    '&': '&amp;',
	    '<': '&lt;',
	    '>': '&gt;',
	    '"': '&quot;',
	    "'": '&#x27;',
	    '`': '&#x60;'
	  };
	  var unescapeMap = _.invert(escapeMap);

	  var createEscaper = function(map) {
	    var escaper = function(match) {
	      return map[match];
	    };
	    var source = '(?:' + _.keys(map).join('|') + ')';
	    var testRegexp = RegExp(source);
	    var replaceRegexp = RegExp(source, 'g');
	    return function(string) {
	      string = string == null ? '' : '' + string;
	      return testRegexp.test(string) ? string.replace(replaceRegexp, escaper) : string;
	    };
	  };
	  _.escape = createEscaper(escapeMap);
	  _.unescape = createEscaper(unescapeMap);

	  _.result = function(obj, path, fallback) {
	    if (!_.isArray(path)) path = [path];
	    var length = path.length;
	    if (!length) {
	      return _.isFunction(fallback) ? fallback.call(obj) : fallback;
	    }
	    for (var i = 0; i < length; i++) {
	      var prop = obj == null ? void 0 : obj[path[i]];
	      if (prop === void 0) {
	        prop = fallback;
	        i = length; 
	      }
	      obj = _.isFunction(prop) ? prop.call(obj) : prop;
	    }
	    return obj;
	  };

	  var idCounter = 0;
	  _.uniqueId = function(prefix) {
	    var id = ++idCounter + '';
	    return prefix ? prefix + id : id;
	  };

	  _.templateSettings = {
	    evaluate: /<%([\s\S]+?)%>/g,
	    interpolate: /<%=([\s\S]+?)%>/g,
	    escape: /<%-([\s\S]+?)%>/g
	  };

	  var noMatch = /(.)^/;

	  var escapes = {
	    "'": "'",
	    '\\': '\\',
	    '\r': 'r',
	    '\n': 'n',
	    '\u2028': 'u2028',
	    '\u2029': 'u2029'
	  };

	  var escapeRegExp = /\\|'|\r|\n|\u2028|\u2029/g;

	  var escapeChar = function(match) {
	    return '\\' + escapes[match];
	  };

	  _.template = function(text, settings, oldSettings) {
	    if (!settings && oldSettings) settings = oldSettings;
	    settings = _.defaults({}, settings, _.templateSettings);

	    var matcher = RegExp([
	      (settings.escape || noMatch).source,
	      (settings.interpolate || noMatch).source,
	      (settings.evaluate || noMatch).source
	    ].join('|') + '|$', 'g');

	    var index = 0;
	    var source = "__p+='";
	    text.replace(matcher, function(match, escape, interpolate, evaluate, offset) {
	      source += text.slice(index, offset).replace(escapeRegExp, escapeChar);
	      index = offset + match.length;

	      if (escape) {
	        source += "'+\n((__t=(" + escape + "))==null?'':_.escape(__t))+\n'";
	      } else if (interpolate) {
	        source += "'+\n((__t=(" + interpolate + "))==null?'':__t)+\n'";
	      } else if (evaluate) {
	        source += "';\n" + evaluate + "\n__p+='";
	      }

	      return match;
	    });
	    source += "';\n";

	    if (!settings.variable) source = 'with(obj||{}){\n' + source + '}\n';

	    source = "var __t,__p='',__j=Array.prototype.join," +
	      "print=function(){__p+=__j.call(arguments,'');};\n" +
	      source + 'return __p;\n';

	    var render;
	    try {
	      render = new Function(settings.variable || 'obj', '_', source);
	    } catch (e) {
	      e.source = source;
	      throw e;
	    }

	    var template = function(data) {
	      return render.call(this, data, _);
	    };

	    var argument = settings.variable || 'obj';
	    template.source = 'function(' + argument + '){\n' + source + '}';

	    return template;
	  };

	  _.chain = function(obj) {
	    var instance = _(obj);
	    instance._chain = true;
	    return instance;
	  };

	  var chainResult = function(instance, obj) {
	    return instance._chain ? _(obj).chain() : obj;
	  };

	  _.mixin = function(obj) {
	    _.each(_.functions(obj), function(name) {
	      var func = _[name] = obj[name];
	      _.prototype[name] = function() {
	        var args = [this._wrapped];
	        push.apply(args, arguments);
	        return chainResult(this, func.apply(_, args));
	      };
	    });
	    return _;
	  };

	  _.mixin(_);

	  _.each(['pop', 'push', 'reverse', 'shift', 'sort', 'splice', 'unshift'], function(name) {
	    var method = ArrayProto[name];
	    _.prototype[name] = function() {
	      var obj = this._wrapped;
	      method.apply(obj, arguments);
	      if ((name === 'shift' || name === 'splice') && obj.length === 0) delete obj[0];
	      return chainResult(this, obj);
	    };
	  });

	  _.each(['concat', 'join', 'slice'], function(name) {
	    var method = ArrayProto[name];
	    _.prototype[name] = function() {
	      return chainResult(this, method.apply(this._wrapped, arguments));
	    };
	  });

	  _.prototype.value = function() {
	    return this._wrapped;
	  };

	  _.prototype.valueOf = _.prototype.toJSON = _.prototype.value;

	  _.prototype.toString = function() {
	    return String(this._wrapped);
	  };

	  if (typeof define == 'function' && define.amd) {
	    define('underscore', [], function() {
	      return _;
	    });
	  }
}());


/**
 * 定义sdui事件模型
 * 
 * @author wangbinbin
 * @date 2016/11/28
 * 
 */
var SdEvent = function () {}

SdEvent.prototype = {
	addListener : function(types,fn){ // 添加事件监听
		var _this = this;
		
		var types = sdutil.trim(types).split(' ');
		
		
		for (var i = 0, type; type = types[i++];) {
			
			_this.getListener(type,true).push(fn)
		}
	}
	,getListener : function(type, force) {
	    var allListeners,_this=this;
	    type = type.toLowerCase();
	    return ( ( allListeners = ( _this._eventList || force && ( _this._eventList = {} ) ) )
	        && ( allListeners[type] || force && ( allListeners[type] = [] ) ) );
	}
	,removeListener : function(types,fn) {
		var _this = this;
		
		types = sdutil.trim(types).split(' ');
        for (var i = 0, type; type = types[i++];) {
        	
        	sdutil.removeItem(_this.getListener(type) || [], fn);
        }
	}
	,removeAllListener : function(types) {
		var _this = this;
		
		types = sdutil.trim(types).split(' ');
        for (var i = 0, type; type = types[i++];) {
        	if(_this._eventList[type]) {
        		_this._eventList[type] = [];
        	}
        }
	}
	,on : function(type,fn) {  // 订阅事件
		var _this = this;
		return _this.addListener(type, fn);
	}
	,un : function(type,fn) { // 取消订阅
		var _this = this;
		
		return _this.removeListener(type,fn);
	}
	,off : function(types) {
		var _this = this;
		
		return _this.removeAllListener(types);
	}
	,fire : function() { // 触发事件
		var _this = this
		if(arguments.length < 1) return _this;
		
		var args = Array.prototype.slice.call(arguments),types=args.shift();
		
		types = sdutil.trim(types).split(' ');
		
		 for (var i = 0, type; type = types[i++];) {
			 var fns = _this.getListener(type);
			 
			 if(fns) {
				 var n = fns.length;
				 while(n--) {
					 if(fns[n]) {
						 fns[n].apply(_this, args);
					 }
				 }
			 }
		 }
		return _this;
	}
};

/**
 * 扩展jquery ajax，对ajax请求进行包装，添加更多个性化设计；
 * 所有基于jquery ajax的操作，都在此类中进行共通化;
 * 
 * @param $ jQuery选择器
 * 
 * @author 	wangbinbin
 * @date	2016/10/18
 */
// 扩展jquery实现
(function($) {
	$.extend({
		sdAjax:function(opt) {
			
			var defaults = {
				url: '', 		// 后台请求地址
				type:'GET',
				dataType:'json',
				contentType: 'application/json',
				cache:false,
				data: "", 	// 请求参数
				async: true, 	// 是否异步
				waitFlag: true, // 是否需要加载等待动画
				successCallback:null,
				errorCallback:null
			}
			var opts = $.extend(true,{},defaults, opt);
			
			var time = new Date().getTime();
			
			if(opts.url.indexOf('?') != -1) {
				opts.url = opts.url + "&time=" + time;
			} else {
				opts.url = opts.url + "?time=" + time;
			}
			
			var mlayer = layer;
			
			if(parent && parent.parent) {
				mlayer = parent.parent.layer;
			} else if (parent) {
				mlayer = parent.layer;
			}
			
			
			// 添加等待动画
			if(opts.waitFlag) {
				mlayer.load();
			}
			
			// 如果contentType为json,则应该序列号用户数据
			if(opts.contentType == 'application/json') {
				opts.data = JSON.stringify(opts.data);
			}
			
			$.ajax({
				type: opts.type,
				dataType: opts.dataType,
				url: opts.url,
				data: opts.data,
				contentType:opts.contentType,
				async: opts.async,
				success: function(data) {
					// 关闭所有加载层
					mlayer.closeAll('loading');
					
					if(sdutil.isFunction(opts.successCallback)) {
						opts.successCallback.call('', data);
					}
				},
				error: function(XMLHttpRequest, txtStatus, errorThrown) {
					
					// 关闭所有加载层
					mlayer.closeAll('loading');
					
					if(sdutil.isFunction(opts.errorCallback)) {
						
						opts.errorCallback.call();
						
					} else {
						// 出错了，给个吐司提示
						message('请求提交有误，请稍后重试');
					}
				}
			});
		}
	});
	
})(jQuery);


/**
 * 目前的弹出层相关UI显示，都是基于layer前端插件；
 * 此封装的意义在于：对调用者透明化依赖的具体插件，同时也便于后期进行扩展或替换第三方依赖，不影响界面的调用
 * 
 * @param $ jQuery选择器
 * 
 * @author 	wangbinbin
 * @date	2016/10/18
 */
(function($) {
	
	var mlayer = layer;
	
	if(parent && parent.parent) {
		mlayer = parent.parent.layer;
	} else if (parent) {
		mlayer = parent.layer;
	}
	
	// 确认框
	window.alert = function(content,callback,autoclose) {
		tmpalert(0,content,callback,autoclose);
	}
	
	// 成功提示框
	success = function(content,callback,autoclose) {
		tmpalert(1,content,callback,autoclose);
	}
	
	// 错误提示框
	error = function(content,callback,autoclose) {
		tmpalert(2,content,callback,autoclose);
	}
	
	function tmpalert(type,content,callback,autoclose) {
		var sdEvent = new SdEvent();
		
		sdEvent.on('close',function(index,callback) {
			
			if(sdutil.isFunction(callback)) {
				callback.call();
			}
			
			mlayer.close(index);
			
			sdEvent.off('close');
		});
		
		var _index = mlayer.alert(content, {icon: type},function(index){
			
			sdEvent.fire('close',index,callback);
		});
		
		// 如果设置了自动关闭，则执行关闭动作
		autoclose == true && sdutil.defer(function(){ sdEvent.fire('close',_index,callback); },2000);
	}
	
	// 确认框
	window.confirm = function(content, successCallback, cannelCallback) {
		
		mlayer.confirm(content, {
				  btn: ['确认','取消'] //按钮
				}, function(index){
					if(sdutil.isFunction(successCallback)) {
						successCallback.call();
					}
					
					mlayer.close(index);
					
				}, function(index){
					
					if(sdutil.isFunction(cannelCallback)) {
						cannelCallback.call();
					}
					
					mlayer.close(index);
				});
	}
	
	// 信息提示框
	message = function(content) {
		mlayer.msg(content);
	}
	
	// 提示
	tip = function(content,id,opts) {
		
		if(opts) {
			mlayer.tips(content, '#'+id, opts);
		}else{
			mlayer.tips(content, '#'+id);
		}
		
	}
	
	// 对话框，用于打开新的弹出页面，为了代码可维护性，目前都采用新页面加载
	dialog = function(opts) {
		
		var defaults = {
				content:'',
				title:'',
				type:2,
				width:'800px',
				height:'90%',
				shadeClose:true,
				scroll:true,
				end:null // 此处应该是一个function
		}
		
		
		var opts = $.extend(true,{},defaults, opts);
		
		var scroll = 'yes';
		if(opts.scroll == false) {
			scroll = 'no';
		}
		
		mlayer.open({
			  type: opts.type,
			  title: opts.title,
			  shadeClose: opts.shadeClose,
			  shade: 0.8,
			  area: [opts.width, opts.height],
			  content: [opts.content,scroll],
			  end:opts.end
			});
	}
	
	// 关闭父弹出层
	closeParent = function() {
		var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
		mlayer.close(index);
	}
	
	closeAll = function(){
		mlayer.closeAll();
	}
	
})(jQuery);


/**
 * sdGrid目的在于简化及统一用户加载列表的操作。用户只要调用此插件，即可轻松生成带分页的列表数据。代码量大大减少
 * 
 * @param $ jQuery选择器
 * 
 * @author 	wangbinbin
 * @date	2016/10/18
 */
(function($) {
	
	// 初始化一些常量信息，便于后期统一调整
	var noresult = '暂无数据'
	
	
	$.fn.sdGrid = function(property) {
		
		var _this = this;
		
		var defaultProperty = {
				url:'',
				cache:false,
				type:'GET',
				pageSize:10,
				async:true,	// 是否异步 
				data:{},  	// 待发送数据
				size: 10, 	// 每页读取记录数
				waitFlag:true, //是否显示等待动画
				style: {
					border:'solid 1px #DCDEDE',
					width:'100%'
				},
				theme:'',  //目前有扩展的两个主题： 简洁主题： sdGrid-brief 简洁绿：sdGrid-brief-red
				showTHead:true,
				headBtns:{	// 定义数据列表 头部按钮区域
					borderBottom:'', // solid 1px #d0dee5
					marginBottom:'10px',
					paddingBottom:'0px',
					paddingLeft:'0px',
					height:'33px',
					innerHtml:function(){
						return '';
					}
				}, 
				colProperty:[	// 定义数据列表显示区域渲染
					 
				],
				callback: function(){
					// 数据加载完成后，希望执行的操作
				},
				pagination: {
					show:true,
					pageSize:10,
					style:{
						color:'#08c'
					}
				}
		}
		
		var mergedProperty = $.extend(true,{},defaultProperty, property);
		
		// 初始化列表数据
		loadingTable(_this,mergedProperty,1);
		
	}
	
	// 初始化头部按钮
	function initHeadBtns(selector,property){
		
		if(property.headBtns.innerHtml) {
			
			var headBtnsContent = property.headBtns.innerHtml.call();
			
			if($.trim(headBtnsContent)!='') {
			
				headBtnArea = document.createElement('div');
				headBtnArea.className = 'top-area';
				
				headBtnArea.innerHTML = headBtnsContent;
				
				selector[0].appendChild(headBtnArea);
			}
		}
		
	}
	
	// 请求加载后台数据
	function loadingTable(selector,property,currentPage) {
		
		$(selector).empty();
		
		$(selector).addClass('sdGrid '+property.theme);
		
		/*------------------------ 初始化表头按钮区域 ----------------------- */
		initHeadBtns(selector,property);
		/*------------------------ 初始化表头按钮区域 ----------------------- */
		
		/*------------------------ 初始化表格区域 -------------------------- */
		table = document.createElement('table');
		
		if(property.showTHead) {
			// 构建table header
			table.appendChild(tableHeadBuilder(property));
		}
		
		// 构建table body
		tbody = document.createElement('tbody');
		
		table.appendChild(tbody);
		
		selector[0].appendChild(table);
		
		/*------------------------ 初始化表格区域 -------------------------- */
		
		var tmpurl = property.url;
		
		if(property.pagination.show && property.pagination.show==true) {
			var pageSize = 10;
			if(property.pagination.pageSize) {
				pageSize = property.pagination.pageSize;
			}
			
			// GET请求，应该是在路径后拼接
			if(property.type=='get' || property.type == 'GET') {
				if(property.url) {
					if(property.url.indexOf('?') > 0) {
						tmpurl += ('&current='+currentPage+'&size='+size);
					}else{
						tmpurl += ('?current='+currentPage+'&size='+size);
					}
				}
				
			}else {
				property.data['current'] = currentPage;
				property.data['size'] = pageSize;
				property.contentType="application/json";
			}
		}
		
		
		$.sdAjax({
			url: tmpurl,
			cache: property.cache,
			dataType: 'json',
			type: property.type,
			data: property.data,
			contentType: property.contentType,
			async: property.async,
			waitFlag:property.waitFlag,
			successCallback: function(data){
				if(data&&data.pages==0){
					data.pages=1;
				}
				// 渲染列表数据
				addTableData(selector,property,data);
				
				// 只有指定显示分页，然后才会显示
				if(property.pagination.show && property.pagination.show==true) {
					// 加载成功后，初始化page样式
					pager(selector,property,data);
				}
			},
			errorCallback: function() {
				noRecordSet(selector,property.colProperty.length);
				
				// 只有指定显示分页，然后才会显示
				if(property.pagination.show && property.pagination.show==true) {
					// 加载成功后，初始化page样式
					var data = {};
					data.current = 1;
					data.total = 0;
					data.pages = 1;
					pager(selector,property,data);
				}
			}
		});
		
	}
	
	function tableHeadBuilder(property) {
		// 创建表格头元素
		thead = document.createElement('thead');
		
		for(var i=0; i<property.colProperty.length; i++) {
			// 创建头cell
			th = document.createElement('th');
			if(property.colProperty[i].style && property.colProperty[i].style.background) {
				th.style.background = property.colProperty[i].style.background;
			}
			
			// 设置头部高度
			if(property.colProperty[i].style && property.colProperty[i].style.height){
				th.style.height = property.colProperty[i].style.height;
				th.style.lineHeight = property.colProperty[i].style.height;
			}
			
			// 设置表头文本对齐方式
			if(property.colProperty[i].style && property.colProperty[i].style.textAlign) {
				th.style.textAlign = property.colProperty[i].style.textAlign;
			}
			
			// 设置表头右边框样式
			if(property.colProperty[i].style && property.colProperty[i].style.borderRight) {
				th.style.borderRight = property.colProperty[i].style.borderRight;
			}
			
			if(property.colProperty[i].style && property.colProperty[i].style.borderBottom) {
				th.style.borderBottom = property.colProperty[i].style.borderBottom;
			}
			
			
			if(property.colProperty[i].style && property.colProperty[i].style.width) {
				th.style.width = property.colProperty[i].style.width;
			}
			
			// 设置字体颜色
			if(property.colProperty[i].style && property.colProperty[i].style.color) {
				th.style.color = property.colProperty[i].style.color;
			}
			
			// 设置字体大小
			if(property.colProperty[i].style && property.colProperty[i].style.fontSize) {
				th.style.fontSize = property.colProperty[i].style.fontSize;
			}
			
			th.innerHTML = property.colProperty[i].colLabel;
			// 将头cell添加至head中
			thead.appendChild(th);
		}
		
		return thead;
	}
	
	function rowUpdateEvent(row,property){
		
		var rowdata = $(row).data('rowrecord');
		
		$(row).empty();
		
		if(property.colProperty) {
			
			// 设置每列单元格
			for(var j=0; j<property.colProperty.length; j++) {
				
				td = document.createElement('td');
				
				tddiv = document.createElement('div');
				
				
				if(property.colProperty[j].colModel && property.colProperty[j].colModel.style && property.colProperty[j].colModel.style.height) {
					td.style.height = property.colProperty[j].colModel.style.height;
//					td.style.lineHeight = property.colProperty[j].colModel.style.height;
				}
				
				if(property.colProperty[j].colModel && property.colProperty[j].colModel.style && property.colProperty[j].colModel.style.width) {
					td.style.width = property.colProperty[j].colModel.style.width;
				}
				
				if(property.colProperty[j].style && property.colProperty[j].style.width) {
					tddiv.style.width = property.colProperty[j].style.width; // 宽度必须设置，否则不出现“省略符”
				}
				

				// 内容超过长度，是否显示省略号
				if(property.colProperty[j].colModel && property.colProperty[j].colModel.style && property.colProperty[j].colModel.style.ellipsis && property.colProperty[j].colModel.style.ellipsis == true) {
					tddiv.style.width = property.colProperty[j].style.width; // 宽度必须设置，否则不出现“省略符”
					tddiv.style.height = td.style.height;
					tddiv.style.lineHeight = td.style.height;
					
					tddiv.style.display = 'block';
					tddiv.style.whiteSpace = 'nowrap';
					tddiv.style.overflow = 'hidden';
					tddiv.style.textOverflow = 'ellipsis';
				}
				
				// 设置数据库右边框样式 
				if(property.colProperty[j].colModel && property.colProperty[j].colModel.style &&  property.colProperty[j].colModel.style.borderLeft) {
					td.style.borderLeft = property.colProperty[j].colModel.style.borderLeft;
				}
				
				// 设置数据行文本对齐方式
				if(property.colProperty[j].colModel && property.colProperty[j].colModel.style &&  property.colProperty[j].colModel.style.textAlign) {
					td.style.textAlign = property.colProperty[j].colModel.style.textAlign;
				}
				
				// 设置字体颜色
				if(property.colProperty[j].colModel && property.colProperty[j].colModel.style &&  property.colProperty[j].colModel.style.color) {
					td.style.color = property.colProperty[j].colModel.style.color;
				}
				
				// 设置字体大小
				if(property.colProperty[j].colModel && property.colProperty[j].colModel.style &&  property.colProperty[j].colModel.style.fontSize) {
					td.style.fontSize = property.colProperty[j].colModel.style.fontSize;
				}
				
				// 设置字体权重
				if(property.colProperty[j].colModel && property.colProperty[j].colModel.style &&  property.colProperty[j].colModel.style.fontWeight) {
					td.style.fontWeight = property.colProperty[j].colModel.style.fontWeight;
				}
				
				tddiv.innerHTML = property.colProperty[j].colModel.innerHtml.call('',rowdata);
				
				td.appendChild(tddiv);
				
				$(row).append(td);
				
			}
			
			// 执行回调
			if(sdutil.isFunction(property.callback)) {
				property.callback.call();
			}
		}
	}
	
	// 初始化列表数据
	function addTableData(selector,property,data) {
		// 每次记载数据时，应该清空数据
		$(selector).children('table').children('tbody').empty();
		
		// 有数据时，初始化遍历记录集，否则设置默认值
		if(data && data.records.length>0) {
			
			for(var i=0; i<data.records.length; i++) {
				
				tr = document.createElement('tr');
				
				if(property.style && property.style.rowbackground) {
					tr.style.background = property.style.rowbackground;
				}
				
				tr.style.borderTop = '1px solid #DCDEDE';
				
				if(i%2 == 1) { // 偶数行，高亮显示
					if(property.style && property.style.evenRowbackground) {
						tr.style.background = property.style.evenRowbackground;
					}
				}
				
				$(tr).data('rowrecord',data.records[i]);
				
				$(tr).bind("update",function(){
					var row = this;
					rowUpdateEvent(row,property);
				});
				
				if(property.colProperty) {
					// 设置每列单元格
					for(var j=0; j<property.colProperty.length; j++) {
						
						td = document.createElement('td');
						
						tddiv = document.createElement('div');
						
						
						// 设置数据行高度
						if(property.colProperty[j].colModel && property.colProperty[j].colModel.style && property.colProperty[j].colModel.style.height) {
							td.style.height = property.colProperty[j].colModel.style.height;
//							td.style.lineHeight = property.colProperty[j].colModel.style.height;
						}
						
						if(property.colProperty[j].colModel && property.colProperty[j].colModel.style && property.colProperty[j].colModel.style.width) {
							td.style.width = property.colProperty[j].colModel.style.width;
						}
						
						if(property.colProperty[j].style && property.colProperty[j].style.width) {
							tddiv.style.width = property.colProperty[j].style.width; // 宽度必须设置，否则不出现“省略符”
						}
						

						// 内容超过长度，是否显示省略号
						if(property.colProperty[j].colModel && property.colProperty[j].colModel.style && property.colProperty[j].colModel.style.ellipsis && property.colProperty[j].colModel.style.ellipsis == true) {
							tddiv.style.width = property.colProperty[j].style.width; // 宽度必须设置，否则不出现“省略符”
							tddiv.style.height = td.style.height;
							tddiv.style.lineHeight = td.style.height;

							tddiv.style.display = 'block';
							tddiv.style.whiteSpace = 'nowrap';
							tddiv.style.overflow = 'hidden';
							tddiv.style.textOverflow = 'ellipsis';
						}
						
						// 设置数据库右边框样式 
						if(property.colProperty[j].colModel && property.colProperty[j].colModel.style &&  property.colProperty[j].colModel.style.borderLeft) {
							td.style.borderLeft = property.colProperty[j].colModel.style.borderLeft;
						}
						
						// 设置数据行文本对齐方式
						if(property.colProperty[j].colModel && property.colProperty[j].colModel.style &&  property.colProperty[j].colModel.style.textAlign) {
							td.style.textAlign = property.colProperty[j].colModel.style.textAlign;
						}
						
						// 设置字体颜色
						if(property.colProperty[j].colModel && property.colProperty[j].colModel.style &&  property.colProperty[j].colModel.style.color) {
							td.style.color = property.colProperty[j].colModel.style.color;
						}
						
						// 设置字体大小
						if(property.colProperty[j].colModel && property.colProperty[j].colModel.style &&  property.colProperty[j].colModel.style.fontSize) {
							td.style.fontSize = property.colProperty[j].colModel.style.fontSize;
						}
						
						// 设置字体权重
						if(property.colProperty[j].colModel && property.colProperty[j].colModel.style &&  property.colProperty[j].colModel.style.fontWeight) {
							td.style.fontWeight = property.colProperty[j].colModel.style.fontWeight;
						}
						
						tddiv.innerHTML = property.colProperty[j].colModel.innerHtml.call('',data.records[i],tr);
						
						td.appendChild(tddiv);
						
						tr.appendChild(td);
					}
				}
				
				$(selector).children('table').children('tbody')[0].appendChild(tr);
				
			}
			
			/*$(selector).children('table').children('tbody').children('tr').hover(function(){
				$(this).addClass('sdgrid-tr-hover');
			},function(){
				$(this).removeClass('sdgrid-tr-hover');
			});
			*/
			
		}else {
			noRecordSet(selector,property.colProperty.length);
		}
		
		// 执行回调
		if(sdutil.isFunction(property.callback)) {
			property.callback.call();
		}
	}
	
	// 无数据行渲染
	function noRecordSet(table,colspan) {
		tr = document.createElement('tr');
		td = document.createElement('td');
		td.colSpan = colspan;
		td.style.lineHeight = '35px';
		td.style.textIndent = '11px';
		td.style.borderRight = 'dotted 1px #c7c7c7';
		td.style.textAlign = 'center';
		td.innerHTML = '暂无数据';
		tr.appendChild(td);
		$(table).children('table').children('tbody')[0].appendChild(tr);
	}
	
	// 分页信息构造器
	function pager(selector,property,data) {
		var currentPage = 1;
		var totalPage = 1;
		
		if(data) {
			currentPage = data.current;
			totalPage = data.pages;
		}
		var pager_div = document.createElement('div');
		pager_div.className='sdgrid-pager';
		
		if(!property.pagination.type || property.pagination.type==0) {
			var message_div = document.createElement('div');
			message_div.style.float = 'left';
			message_div.style.marginTop = '10px';
			message_div.style.marginBottom = '10px';
			message_div.addClass = 'message';
			message_div.innerHTML = '共 <i class="highlight">'+data.total+'</i> 条记录，当前显示第 <i class="highlight">'+currentPage+'</i> 页';
			pager_div.appendChild(message_div);
		}
		var pagination_div = document.createElement('div');
		pagination_div.id = 'sdgrid-page'+new Date().getTime();
		pagination_div.style.float = 'right';
		pager_div.appendChild(pagination_div);
		
		pager_div.style.width = property.style.width;
		
		selector[0].appendChild(pager_div);
		
		
		// 初始化分页属性
		var options = {
            currentPage: currentPage,
            totalPages: totalPage,
            itemTexts: function (type, page, current) {
				switch (type) {
				case "first":
					return "首页";
				case "prev":
					return "上一页";
				case "next":
					return "下一页";
				case "last":
					return "末页";
				case "page":
					return page;
				}
			},
			tooltipTitles: function (type, page, current) {
                    switch (type) {
                    case "first":
                        return "首页";
                    case "prev":
                        return "上一页";
                    case "next":
                        return "下一页";
                    case "last":
                        return "末页";
                    case "page":
                        return "第" + page + "页";
                    }
            },
            onPageClicked: function(event, originalEvent, type,page) {
            	// 点击链接时，应该重新加载数据
            	loadingTable(selector,property,page);
            	
            }
      }
		$('#'+pagination_div.id).bootstrapPaginator(options);
		
//		<div class="sdgrid-pager">
//			<div class="message" style="float:left;">共 <i class="blue">1256</i> 条记录，当前显示第 <i class="blue">1</i> 页</div>
//			<div id="sdgrid-page" style="float:right;"></div>
//		</div>
	}
	
})(jQuery);


/**
 * sdPullList目的在于简化及统一用户加载列表的操作。用户只要调用此插件，即可轻松生成带分页的列表数据。代码量大大减少
 * 
 * @param $ jQuery选择器
 * 
 * @author 	wangbinbin
 * @date	2017/1/5
 */
(function($) {
	
	// 初始化一些常量信息，便于后期统一调整
	var noresult = '暂无数据'
	
	
	$.fn.sdPullList = function(property,records) {
		
		var _this = this;
		
		var defaultProperty = {
				url:'',
				cache:false,
				type:'GET',
				pageSize:10,
				async:true,	// 是否异步 
				data:{},  	// 待发送数据
				colProperty:[	// 定义数据列表显示区域渲染
					 
				],
				theme:'', // 指定下拉加载主题
				callback: function(){
					// 数据加载完成后，希望执行的操作
				},
				pagination: {
					show:true,
					pageSize:10
				}
		}
		
		var mergedProperty = $.extend(true,{},defaultProperty, property);
		
		
		$(_this).empty();
		$(_this).addClass('sd-pull-list');
		
		if(mergedProperty.theme) {
			$(_this).addClass(mergedProperty.theme);
		}
		
		if(records) {
			for(var i=0; i<records.length; i++) {
				createPullListItem(_this,mergedProperty,records[i]);
			}
		}else{
			// 加载列表数据
			loadPullList(_this,mergedProperty,1);
		}
		
	}
	
	function loadPullList(selector,property,currentPage) {
		$(selector).find('div.action').remove();
		
		var tmpurl = property.url;
		if(property.pagination.show && property.pagination.show==true) {
			var pageSize = 10;
			if(property.pagination.pageSize) {
				pageSize = property.pagination.pageSize;
			}
			
			// GET请求，应该是在路径后拼接
			if(property.type=='get' || property.type == 'GET') {
				if(property.url) {
					if(property.url.indexOf('?') > 0) {
						tmpurl += ('&page='+currentPage+'&pageSize='+pageSize);
					}else{
						tmpurl += ('?page='+currentPage+'&pageSize='+pageSize);
					}
				}
				
			}else {
				property.data['page'] = currentPage;
				property.data['pageSize'] = pageSize;
				property.contentType="application/json";
			}
		}
		
		
		$.sdAjax({
			url: tmpurl,
			cache: property.cache,
			dataType: 'json',
			type: property.type,
			data: property.data,
			contentType: property.contentType,
			async: property.async,
			waitFlag:property.waitFlag,
			successCallback: function(data){
				// 渲染列表数据
				// 有数据时，初始化遍历记录集，否则设置默认值
				if(data && data.records.length>0) {
					
					for(var i=0; i<data.records.length; i++) {
						createPullListItem(selector,property,data.records[i]);
					}
					
					sdutil.isFunction(property.callback) && property.callback.call();
					
				}
				
				/*addTableData(selector,property,data);*/
				
				// 只有指定显示分页，然后才会显示
				if(property.pagination.show && property.pagination.show==true) {
					// 加载成功后，初始化page样式
					pager(selector,property,data);
				}
			},
			errorCallback: function() {
				$(selector).find('div.action').text('没有更多了~');
			}
		});
		
	}
	
	function createPullListItem(selector,property,data) {
		var table = document.createElement('table'),tbody = document.createElement('tbody'),tr = document.createElement('tr'),td,tddiv;
		
		if(property.colProperty) {
			
			// 设置每列单元格
			for(var j=0; j<property.colProperty.length; j++) {
				
				td = document.createElement('td');
				
				
				if(property.colProperty[j].colModel && property.colProperty[j].colModel.style && property.colProperty[j].colModel.style.background) {
					td.style.background = property.colProperty[j].colModel.style.background;
				}
				
				if(property.colProperty[j].colModel && property.colProperty[j].colModel.style && property.colProperty[j].colModel.style.height) {
					td.style.height = property.colProperty[j].colModel.style.height;
//					td.style.lineHeight = property.colProperty[j].colModel.style.height;
				}
				
				if(property.colProperty[j].colModel && property.colProperty[j].colModel.style && property.colProperty[j].colModel.style.width) {
					td.style.width = property.colProperty[j].colModel.style.width;
				}

				// 设置数据行文本对齐方式
				if(property.colProperty[j].colModel && property.colProperty[j].colModel.style &&  property.colProperty[j].colModel.style.textAlign) {
					td.style.textAlign = property.colProperty[j].colModel.style.textAlign;
				}
				
				td.innerHTML = property.colProperty[j].colModel.innerHtml.call('',data);
				
				
				$(tr).append(td);
				
			}
			
		}
		
		tbody.appendChild(tr);
		table.appendChild(tbody);
		
		selector[0].appendChild(table);
	}
	
	// 分页信息构造器
	function pager(selector,property,data) {
		
		var currentPage = 1;
		
		if(data) {
			currentPage = data.current;
		}
		
		var action_div = document.createElement('div');
		action_div.className = 'action';
		
		if(currentPage == data.pages) {
			action_div.innerHTML = '没有更多了~';
		}else{
			var button_div = document.createElement('div');
			button_div.className = 'button';
			action_div.appendChild(button_div);
		}
		
		selector[0].appendChild(action_div);
		
		
		$(button_div).click(function(){
			loadPullList(selector,property,currentPage+1);
		});
	}
	
})(jQuery);


/**
 * 自考平台左侧菜单显示插件
 * 功能：1) 显示属性手风琴菜单
 * 	   2) 点击收缩展开
 * 	   3) 提供默认展开 及 默认打开指定菜单
 * 	   4) 提供菜单加载完的回调封装
 *     5) 初始化时是否展开菜单
 * 
 * @param $ jQuery选择器
 * 
 * @author 	wangbinbin
 * @date	2016/11/10
 */
(function($){
	
	$.fn.sdMenu = function(opt) {
		
		var _this = this;
		
		var defaults = {
			pClass:'leftmenu',
			titleClass:'title',
			titleNameClass:'titleName',
			itemClass:'menuson',
			activeItemClass:'active',
			linkNameClass:'linkName',
			target:'contentFrame',
			expand: false,
			menus:[],
			defaultOpen:{pMenu:null,cMenu:null} // 默认打开菜单及页面序号
			,callback:null // 数据加载完的回调函数
		}
		
		var opts = $.extend(true,{},defaults, opt);
		
		$(_this).empty();
		var menudl = document.createElement('dl');
		menudl.className = opts.pClass;
		
		for(var i=0; i<opts.menus.length;i++) {
		
			var menu = opts.menus[i];
			
			var menuItemDd = document.createElement('dd');
			
			// 创建每一项菜单的title
			var menuItemTitle = document.createElement('div');
			menuItemTitle.className = opts.titleClass;
			
			var titleSpan = document.createElement('span');
			titleSpan.className = opts.titleNameClass;
			titleSpan.innerHTML = menu.name;
			menuItemTitle.appendChild(titleSpan);
			
			var titleImgSpan = document.createElement('span');
			var titleImg = document.createElement('img');
			titleImg.className = 'arrow';
			titleImg.src = '../images/arrow-right.png';
			titleImgSpan.appendChild(titleImg);
			menuItemTitle.appendChild(titleImgSpan);
			
			menuItemDd.appendChild(menuItemTitle)
			
			// 创建每一项菜单的子菜单
			var menuItemSub = document.createElement('ul');
			menuItemSub.className = opts.itemClass;
			
			if(opts.expand == false) {
				menuItemSub.style.display = 'none';
			}	else {
				menuItemSub.style.display = '';
			}
			
			for(var j=0; j<menu.subMenus.length; j++) {
				
				
				var menuItemSubItem = document.createElement('li');
				var cite = document.createElement('cite');
				menuItemSubItem.appendChild(cite);
				
				var link = document.createElement('a');
				link.className = opts.linkNameClass;
				link.href = menu.subMenus[j].link;
				link.target = opts.target;
				link.innerHTML = menu.subMenus[j].name;
				menuItemSubItem.appendChild(link);
				
				var iElement = document.createElement('i');
				menuItemSubItem.appendChild(iElement);
				
				menuItemSub.appendChild(menuItemSubItem);
				
			}
			menuItemDd.appendChild(menuItemSub);
			
			menudl.appendChild(menuItemDd);
		}
		_this[0].appendChild(menudl);
		
		if(sdutil.isFunction(opts.callback)) {
			opts.callback.call();
		}
		
		
		$(_this).find(".menuson li").click(function(){
			$(_this).find(".menuson li.active").removeClass("active")
			$(this).addClass("active");
		});
		
		$(_this).find('.title').click(function(){
			var $ul = $(this).next('ul');
			$(_this).find('dd').find('ul').slideUp();
			$(_this).find('.title').removeClass('titleactive');
			$(_this).find('.title').find('.arrow').attr('src','../images/arrow-right.png');
			if($ul.is(':visible')){
				$(this).next('ul').slideUp(function(){
					
				});
				
			}else{
				$(this).next('ul').slideDown(function(){
			    
				});
				
				$(this).addClass('titleactive');
				$(this).find('.arrow').attr('src','../images/arrow-down-white.png');
			}
			
		});
		
		// 数据加载完后，执行回调
		if(sdutil.isFunction(opts.callback)) {
			opts.callback.call();
		}
		
		// 如果有设置默认打开，则执行默认打开页
		if(opts.defaultOpen && opts.defaultOpen.pMenu) {
			$(_this).find('.title:eq('+(opts.defaultOpen.pMenu-1)+')').click();
		}
		
		if(opts.defaultOpen && opts.defaultOpen.cMenu) {
			$(_this).find('ul:visible').find('li:eq('+(opts.defaultOpen.cMenu-1)+')').click();
			$(_this).find('ul:visible').find('li.active a')[0].click();
		}
	}
	
})(jQuery);


/**
 * sdSelect 
 * 	1、提供统一的select样式，
 * 	2、效果增强
 *  3、方便与双向绑定插件整合
 *  
 * @param $
 * 
 * @author wangbinbin
 * @date 2016/11/14
 * 
 */
(function($){
	
	$.sdSelect = function(opt) {
		
		var defaults = {
				ractive:null, // ractive对象，便于与业务系统整合，如果非ractive框架，此项无效设置
				callback:null
		}
		
		var opts = $.extend(true,{},defaults, opt);
		
		// 监听页面是否有新添加select
		$('html').bind('DOMNodeInserted',function(e) {
			
			if($(e.target).is("select")) {
				
				initSdSelect(e.target,opts);
			}
			
			if($(e.target).find('select').size()>0) {
				$(e.target).find('select').each(function(){
					initSdSelect(this,opts);
				});
				
				// 对新添加的select绑定监听事件
				$(e.target).find('select').bind('DOMNodeInserted',function(e) {
				    initSdSelect(this,opts);
				});
				
				$(e.target).find('select').bind('DOMNodeRemoved',function(e) {
					
				    setTimeout(initSdSelectMonitor(this,opts),50);
				});
			}
		});
		
		// 监听元素的select选项变化
		$('select').bind('DOMNodeInserted',function(e) {
			
		    initSdSelect(this,opts);
		});
		// 监听元素的select选项变化
		$('select').bind('DOMNodeRemoved',function(e) {
			
		    setTimeout(initSdSelectMonitor(this,opts),50);
		});
		
		$('select').each(function(){
			
			initSdSelect(this,opts);
			
		});
		
		
		function initSdSelectMonitor(currentSelect,opts) {
			
			return function(){
				initSdSelect(currentSelect,opts);
			}
		}
		
		function initSdSelect(currentSelect,opts) {
			var _this = currentSelect;
			
			$(_this).show(); // 先显示select，便于测量宽度
			var _width = $(_this).width();
			$(_this).hide(); // 获取完宽度后，隐藏
			
			var currentSelectName = $(_this).attr('name');
			
			if(!currentSelectName) {
				
				var selectName = 'select'+new Date().getTime()+Math.random();
				$(_this).attr('name',selectName);
				currentSelectName = selectName;
			}
			
			$(_this).next('.sdSelect-container').remove();
			
			var selectContainer = document.createElement('div');
			selectContainer.style.display = 'inline-block';
			selectContainer.className = 'sdSelect-container';
			
			// 创建输入框
			var selectInput = document.createElement('input');
			selectInput.className = 'sdui-select sdSelect-input';
			selectInput.name = currentSelectName+'-sdSelect';
			
			selectInput.style.width = _width+'px';
			selectInput.style.height = $(_this).height()+'px';
			selectInput.style.textIndent = '0';
			selectInput.style.padding = '0 18px 0 5px';
		    /* text-indent: 5px; */
			//selectInput.style.paddingRight = '10px';
			
			selectInput.readOnly = 'readonly';
			selectInput.disabled = $(_this).attr('disabled');
			
			$(selectContainer).append(selectInput);
			
			var selectItemContainer = document.createElement('ul');
			selectItemContainer.style.listStyle = 'none';
			selectItemContainer.style.margin = '0px';
			selectItemContainer.style.padding = '0px';
			selectItemContainer.style.maxHeight = '200px';
			selectItemContainer.style.overflowY = 'auto';
			selectItemContainer.style.overflowX = 'hidden';
			selectItemContainer.style.textAlign = 'left';
			
			selectItemContainer.style.width = _width+'px';
			selectItemContainer.style.background = '#fff';
			
			selectItemContainer.style.border = '1px solid #e6e6e6';
			/*if($(_this).css('border')) {
				selectItemContainer.style.border = $(_this).css('border');
			}*/
			selectItemContainer.style.zIndex = '9999';
			selectItemContainer.style.position = 'absolute';
			
			selectItemContainer.style.display = 'none'; // 下拉框默认不显示
			
			// 判断是否是多行选择框
			var isMultiple = false
			if(typeof($(_this).attr('multiple'))!="undefined") {
				isMultiple = true;
			}
			
			$(_this).children('option').each(function(index){
				
				var selectItem = document.createElement('li');
				selectItem.style.padding = '5px 10px';
				selectItem.style.width = selectItemContainer.style.width;
				selectItem.style.display = 'block';
				//selectItem.style.height = '2em';
				selectItem.style.lineHeight = '2em';
				selectItem.style.whiteSpace = 'nowrap';
				selectItem.style.overflow = 'hidden';
				selectItem.style.textAlign = 'left';
				selectItem.style.textOverflow = 'ellipsis';
					
				selectItem['setAttribute']('data', $(this).attr('value'));// 绑定option的value
				selectItem.title = $(this).text();   // 绑定title
				selectItem.innerHTML = $(this).text();   // 绑定option的下拉选项
				selectItemContainer.appendChild(selectItem);
				
				
				
			});
			
			$(selectContainer).append(selectItemContainer);
			$(_this).after(selectContainer);
			
			
			// initSelectedValue(_this,selectInput,isMultiple);
			bindDropdownListEvent(_this,selectInput,isMultiple);
			
			$(selectInput).click(function(e){
				
				$('.sdSelect-container').find('ul').hide();
				
				$(this).next('ul').show();
				$(this).addClass("sdSelect-input-up");
				
				e.stopPropagation();
			});
			
			$(document).click(function(){
				$('.sdSelect-container').find('ul').hide();
				$(this).removeClass("sdSelect-input-up");
			});
		
		}
		
		setInterval(function(){
			
			$('select').each(function(){
				var _this = this;
				
				// 判断是否是多行选择框
				var isMultiple = false
				if(typeof($(_this).attr('multiple'))!="undefined") {
					isMultiple = true;
				}
				
				
				initSelectedValue(_this,$(_this).next("div.sdSelect-container").find("input.sdSelect-input")[0],isMultiple);
			});
			
		},50);
		
		
		function bindDropdownListEvent(_this,selectInput,isMultiple){
			$(selectInput).next('ul').find('li').click(function(e){
				
				$(selectInput).next('ul').find('li').removeClass('selected');
				
				if(!isMultiple){
					
					// 表示单选
					$(_this).val($(this).attr('data'));
					
					var displayText = '';
					$(selectInput).next('ul').find('li').each(function(){
						
						if($(this).attr('data') == $(_this).val()) {
							
							$(this).addClass('selected');
							
							displayText = $(this).text();
							
						}
					});
					$(selectInput)[0].title = displayText;
					$(selectInput).val(displayText);
					
					$(selectInput).next('ul').hide();
					$(selectInput).removeClass("sdSelect-input-up");
					
					// 执行回调函数
					if(sdutil.isFunction(opts.callback)) {
						opts.callback.call();
					}
					
				} else {
					// 多选
					var mVals = $(_this).val();
					
					if(mVals == null) {
						mVals = new Array();
					}
					
					
					if(mVals.indexOf($(this).attr('data')) != -1) {
						
						mVals.remove($(this).attr('data'));
					} else {
						mVals.push($(this).attr('data'));
					}
					
					$(_this).val(mVals);
					
					var displayText = '';
					
					for(var i=0; i<mVals.length; i++) {
						$(selectInput).next('ul').find('li').each(function(){
							if($(this).attr('data') == mVals[i]) {
								
								$(this).addClass('selected');
								
								if(displayText == '') {
									displayText += $(this).text();
								} else {
									displayText += (' , '+$(this).text());
								}
								
							}
						});
					}
					$(selectInput)[0].title = displayText;
					$(selectInput).val(displayText);
					
					$(selectInput).next('ul').show();
					
					// 执行回调函数
					if(sdutil.isFunction(opts.callback)) {
						opts.callback.call();
					}
				}
				
				if(opts.ractive) {
					opts.ractive.updateModel();
					
					if(opts.ractive.find('select[name="'+$(_this).attr('name')+'"]')) { // 如果找不到，就不用触发change事件
						var event=document.createEvent("HTMLEvents");
						event.initEvent("change",false,true);
						opts.ractive.find('select[name="'+$(_this).attr('name')+'"]').dispatchEvent(event);
					}
				}
				
				// 这是对于普通jquery触发
				$(_this).trigger('change');
				
				e.stopPropagation();
			});
		}
		
		function initSelectedValue(_this,selectInput,isMultiple) {
			
			$(selectInput).next('ul').find('li').removeClass('selected');
			
			if($(_this).is(':disabled')) {
				selectInput.disabled = 'disabled';
			}
			
			// 检查是否有验证不通过高亮
			if($(_this).hasClass('error')) {
				$(selectInput).addClass('error');
			} else {
				$(selectInput).removeClass('error');
			}
			
			// 初始化值时，如果实际选项与sdSelect选项数不匹配，则再做一下option同步
			if($(_this).find('option').text()  !=  $(selectInput).next('ul').find('li').text()) {
				
				$(selectInput).next('ul').find('li').remove();
				
				$(_this).children('option').each(function(index){
					
					var selectItem = document.createElement('li');
					selectItem.style.padding = '5px 10px';
					selectItem.style.width = $(selectInput).next('ul').width()+'px';
					selectItem.style.display = 'block';
					selectItem.style.height = '2em';
					selectItem.style.lineHeight = '2em';
					selectItem.style.whiteSpace = 'nowrap';
					selectItem.style.overflow = 'hidden';
					selectItem.style.textAlign = 'left';
					selectItem.style.textOverflow = 'ellipsis';
						
					selectItem['setAttribute']('data', $(this).attr('value'));// 绑定option的value
					selectItem.title = $(this).text();   // 绑定title
					selectItem.innerHTML = $(this).text();   // 绑定option的下拉选项
					$(selectInput).next('ul').append(selectItem);
				});
				bindDropdownListEvent(_this,selectInput,isMultiple);
			}
			
			// 调整鼠标划过样式
			$(selectInput).next('ul').find('li').hover(function(){
				$(this).addClass('item-hover');
			},function(){
				$(this).removeClass('item-hover');
			});
			
			if(!isMultiple){
				
				// 表示单选
				var displayText = '';
				$(selectInput).next('ul').find('li').each(function(){
					
					if($(this).attr('data') == $(_this).val()) {
						
						$(this).addClass('selected');
						
						displayText = $(this).text();
					}
				});
				
				$(selectInput)[0].title = displayText;
				$(selectInput).val(displayText);
				
			} else {
				// 多选
				var mVals = $(_this).val();
				
				var displayText = '';
				if(mVals) {
					for(var i=0; i<mVals.length; i++) {
						$(selectInput).next('ul').find('li').each(function(){
							if($(this).attr('data') == mVals[i]) {
								
								$(this).addClass('selected');
								
								if(displayText == '') {
									displayText += $(this).text();
								} else {
									displayText += (' , '+$(this).text());
								}
								
							}
						});
					}
				}
				$(selectInput)[0].title = displayText;
				$(selectInput).val(displayText);
			}
		}
	}
	
	
})(jQuery);


/**
 * 
 * 自定义tab组件，用于渲染tab风格页面显示
 * 
 * @param $ jQuery选择器
 * 
 * @author 	wangbinbin
 * @date	2016/10/18
 */
(function($) {
	var click_event = 'click';
	var enter_event = 'mouseenter';
	
	$.fn.sdTab = function(opts) {
		var _this = this;	// 指向当前选择器
		var isInit = false;	// 表示是否初始化
		var callbackXHistory = []; // 此数组用于防止执行历史，表明对应回调是否已经执行过
		
		
		var defaults = {
				type:1, // 1：点击切换，2：滑过切换，3：自动切换
				duration:200, // duration只有在type=2/type=3时有效，其他情况无需设置
				tabMenuClass:'sdui-tab-menu',	// tab菜单默认的样式
				tabMenuActiveClass:'active',	// tab当前菜单默认样式
				tabContentClass:'sdui-tab-content',	// tab内容默认样式
				callbacks:[],	// 回调函数数组，对应每个tab显示时的回调函数，数组顺序与tab顺序一致
				callOnce:true	// 回调函数是否调用1次， 如果每次展示时都触发，则将此项置为false
		}
		
		var opts = $.extend(true,{},defaults, opts);
		
		// 初始化tab显示
		initTabShow();
		
		var menu_event = click_event;
		
		switch(opts.type) {
			case 1:
				menu_event = click_event;
				break;
			case 2:
				menu_event = enter_event;
				break;
			case 3:
				break;
		}
			
		if(opts.type == 3) { // 自动播放
			
			var intertime = setInterval(autoSwitchTab,opts.duration);
			
			$(_this).hover(function(){
				clearInterval(intertime);
			},function(){
				intertime = setInterval(autoSwitchTab,opts.duration);
			});
			
		}else{ // 其他类型的切换方式
			$(_this).find('.'+opts.tabMenuClass).children().bind(menu_event,function(e){
				// tab菜单点击，切换菜单样式
				if (menu_event == click_event) {
					switchTab($(this));
				} else if (menu_event == enter_event){
					var hoverTime = setTimeout(switchTabWrap($(this)),opts.duration);
					
					$(this).mouseout(function(){
						clearTimeout(hoverTime);
					});
				}
			});
		}
		
		/**
		 * 初始化tab的显示
		 */
		function initTabShow(){
			
			var currentMenu = null;
			
			if($(_this).find('.'+opts.tabMenuClass).children('.'+opts.tabMenuActiveClass).length==0){
				currentMenu = $(_this).find('.'+opts.tabMenuClass).children().eq(0);
				
			}else{
				currentMenu = $(_this).find('.'+opts.tabMenuClass).children('.'+opts.tabMenuActiveClass).eq(0);
			}
			
			isInit = true;
			
			switchTab(currentMenu);
		}
		
		/**
		 * 自动切换tab菜单
		 */
		function autoSwitchTab() {
			
			// 切换菜单
			var currentActiveMenu = $(_this).find('.'+opts.tabMenuClass).children('.'+opts.tabMenuActiveClass);
			var toShowMenu = currentActiveMenu.next();
			
			if(toShowMenu.length==0) {
				toShowMenu = $(_this).find('.'+opts.tabMenuClass).children().first();
			}
			
			// 切换content
			switchTab(toShowMenu);
		}
		
		
		/**
		 * 此包装函数，用于setTimeout调用
		 */
		function switchTabWrap(currentMenu) {
			
			return function(){
				switchTab(currentMenu);
			}
		}
		
		/**
		 *  切换tab
		 */
		function switchTab(currentMenu) {
			// 如果切换的选项还是当前的激活选项，则不应该做任何操作
			
			if(!currentMenu.hasClass('disabled')) {
				if($(_this).find('.'+opts.tabMenuClass).children('.'+opts.tabMenuActiveClass).index() != currentMenu.index() || isInit == true) {
					
					isInit = false;
					
					currentMenu.siblings().removeClass(opts.tabMenuActiveClass);
					currentMenu.addClass(opts.tabMenuActiveClass);
					
					$(_this).find('.'+opts.tabContentClass).children().hide();
					$(_this).find('.'+opts.tabContentClass).children().eq(currentMenu.index()).show();
					
					// 执行各自回调函数
					if(sdutil.isFunction(opts.callbacks[currentMenu.index()])) {
						
						if(opts.callOnce != true || callbackXHistory[currentMenu.index()] != true) {
							opts.callbacks[currentMenu.index()].call();		// 调用对应tab回调函数
							callbackXHistory[currentMenu.index()] = true;	// 标记回调函数执行历史
						}
						
					}
					
				}
			}
		}
	}
	
	
})(jQuery);


/**
 * sdValidate目前是依赖jquery validate， 此插件进行了个性化封装
 * 
 * @param $ jQuery选择器
 * 
 * @author 	wangbinbin
 * @date	2016/10/18
 */
(function($){
	
	$.fn.sdValidate = function(opts) {
		var _this = this;
		
		// 给待校验区域应用表单验证class
		$(_this).focus(true);		// 初始化时，光标置于第一个可输入框时，体验性不太好，此处设置焦点在form上
		$(_this).addClass('sd-validate');
		
		// 默认属性
		var defaults = {
			ignore: [],
			onkeyup:null,	// 关闭键盘事件
			onfocusin:null,	// 光标移入时，暂时不做效果
			focusInvalid:false, // true：光标定位到验证失败项，false：验证失败时，光标无需定位
			onfocusout: function(element) {
				var _theEle = this;
				
				// 移除验证样式
				$(element).removeClass('valid');
				$(element).removeClass('error');
				
				if(opts.focusout != false) { // 如果用户不指定为false，则默认执行 失去光标校验
					
					if($(element).hasClass('Wdate')) return;
					
					if($(element).hasClass('sdSelect-input') && $(element).parent().prev()[0]) {
						element = $(element).parent().prev()[0];
					}
					
					sdutil.defer(function(){
						_theEle.element( element );
					}, 200);
				}
			},
			submitHandler: function(){
				if(opts.action && sdutil.isFunction(opts.action)) {
					opts.action.call();
				}
			},
			showErrors: function(errorMap, errorList) {
				// 先移除所有error样式
				$(_this).find(".error").removeClass("error");
				
				for(var i=0; i<errorList.length; i++){
					
					var invalidElementIndex = $("[name="+"'"+errorList[i].element.name+"'"+"]").index($(errorList[i].element));
					
					// 给invalid元素应用样式
					$("[name="+"'"+errorList[i].element.name+"'"+"]:eq("+invalidElementIndex+")").addClass("error");
						
					// 判断是否设置了提示信息显示
					if(opts.messageShow) {
						// 提示信息是否需要显示
						if(opts.messageShow.show && opts.messageShow.show==true) {
							
							// 根据不同类型，显示对应风格样式
							if(opts.messageShow.type == 1) {
								// 默认显示
								this.defaultShowErrors();
								
							}else if(opts.messageShow.type == 2) {
								// 警告框显示
								alert(errorList[i].message);
								
								break; // 对于警告框，应该只显示一个，多了体验性不好
								
							}else if(opts.messageShow.type == 3) {
								
								// 允许显示多个
								var tipOpts = {tipsMore: true};
								
								if($("[name="+"'"+errorList[i].element.name+"'"+"]:eq("+invalidElementIndex+")").attr("id")) {
									// tip方式显示
									tip(errorList[i].message, $("[name="+"'"+errorList[i].element.name+"'"+"]:eq("+invalidElementIndex+")").attr("id"),tipOpts);
								} else {
									message('警告：使用tip提示方式，元素必须要配置id属性！');
								}
								
							}else if(opts.messageShow.type == 4) {
								// 吐司方式显示
								message(errorList[i].message);
								
								break; // 吐司应该一个一个显示
							}else {
								// 吐司方式显示
								message(errorList[i].message);
								
								break; // 吐司应该一个一个显示
							}
							
						}
					}
					
				}
				
				
			}
		};
		// 合并校验属性
		var opts = $.extend(true,{},defaults,opts);
		
		// 调用jquery validation插件
		$(_this).validate(opts);
	}
	
})(jQuery);

/**
 * checkbox的全选交互组件
 * 
 * @author wangbinbin
 * @date 2016/11/1
 */ 
(function($){
	$.extend({
		sdCheckAll:function(opts) {
		
			var defaults = {
					pName:'cb-all',
					cName:'cb-item'
			}
			
			var mergedOpts = $.extend(true,{},defaults, opts);
			
			// 监听全选项的change事件
			$('input[name='+mergedOpts.pName+']:checkbox').change(function(){
				
				var selectAllItemCB = false;
				
				if($(this).is(':checked')) { // all选项如果是checked状态，则子checkbox都应该被选中
					selectAllItemCB = true;
				}
				$('input[name='+mergedOpts.cName+']:checkbox').prop('checked',selectAllItemCB);
			});
			
			// 监听checkbox下的每一项change事件
			$('input[name='+mergedOpts.cName+']:checkbox').change(function(){
				
				var selectAllCB = false;
				// 如果选中的子选项与子选项个数一致，则应选中全选
				if($('input[name='+mergedOpts.cName+']:checkbox').size() == $('input[name='+mergedOpts.cName+']:checkbox:checked').size()) {
					selectAllCB = true;
				}
				
				$('input[name='+mergedOpts.pName+']:checkbox').prop('checked',selectAllCB);
				
			});
		}
	});
	
})(jQuery);

/**
 * sdTree插件：统一项目中树形结构定义以及实现静态与动态方式加载tree，对开发人员屏蔽样式与事件
 * 
 * @author wangbinbin
 * @date 2016/11/25
 */ 
(function($){
	
	var treeEvent = new SdEvent();
	
	treeEvent.on('load',function(root,property) {
		if(property.type=='post' || property.type == 'POST') {
			property.contentType="application/json";
		}
		
		$.sdAjax({
			url: property.url,
			cache: property.cache,
			dataType: 'json',
			type: property.type,
			data: property.data,
			contentType: property.contentType,
			async: property.async,
			waitFlag:property.waitFlag,
			successCallback: function(data){
				if(data && data.data) {
					treeEvent.fire('draw',root,data.data,property);
				}
			}
		});
	});
	
	treeEvent.on('draw',function(treeroot, root, data,property,currentLevel) {
		
		$(treeroot).empty();
		
		$(treeroot).css('position','relative');
		
		if(root && sdutil.isArray(data) && data.length>0) {
			
				var nodeContainer = document.createElement('ul');
				
				if(treeroot === root) {
					nodeContainer.className = 'sdTree';
					
				} else {
					if(property && property.style.icon && property.style.icon.show) {
						nodeContainer.className = 'ul-bg';
					}
				}
				
				if(currentLevel) {
					currentLevel += 1;
				} else {
					currentLevel = 1;
				}
				
				for(var i=0; i<data.length; i++) {
					
					var node = document.createElement('li'),nodeItemContainer = document.createElement('div'),nodeIcon = document.createElement('div'),nodeTitle = document.createElement('div');
					
					
					if(data[i].checked==true) {
						nodeItemContainer.className = 'tree-item tree-title-selected';
					}else{
						nodeItemContainer.className = 'tree-item';
					}
					nodeIcon.className = 'tree-icon';
					nodeItemContainer.appendChild(nodeIcon);
					
					nodeTitle.className = 'tree-title';
					
					if(property.style.select.show==true) {
						if(property.style.select.multiple==true) {
							
							var treeCheck = '';
							if(!property.isEditable) {
								treeCheck = '<div class="tree-check"></div>';
							}
							
							
							nodeTitle.innerHTML = treeCheck+' '+property.nodeInnerHtml.call('', data[i]);
						} else {
							var treeCheck = '';
							if(!property.isEditable) {
								treeCheck = '<div class="tree-check"></div>';
							}
							
							if(sdutil.isArray(data[i][property.childName]) && data[i][property.childName].length>0) {
								nodeTitle.innerHTML = treeCheck + ' '+ property.nodeInnerHtml.call('', data[i]);
							}else{
								nodeTitle.innerHTML = treeCheck + ' '+property.nodeInnerHtml.call('', data[i]);
							}
						}
					
					}else{
						nodeTitle.innerHTML = property.nodeInnerHtml.call('', data[i]);
					}
					
					
					if(sdutil.isArray(fields=property.fields) && (data[i].nodeadd || data[i].nodeedit)) {
						for(var j=0;j<fields.length;j++) {
							$(nodeTitle).find('[field="'+fields[j].name+'"]').html(fields[j].width?'<input type="text" name="'+fields[j].name+'" class="sdui-input" mandantory="'+fields[j].required+'" maxlength="'+fields[j].length+'" style="width:'+fields[j].width+'" value="'+data[i][fields[j].name]+'" />':'<input type="text" name="'+fields[j].name+'" class="sdui-input" maxlength="'+fields[j].length+'"  value="'+data[i][fields[j].name]+'" />');
						}
					}
					
					
					nodeItemContainer['setAttribute']('currentLevel',currentLevel);
					node['setAttribute']('currentLevel',currentLevel);
					
					nodeItemContainer.appendChild(nodeTitle);
					node.appendChild(nodeItemContainer);
					nodeContainer.appendChild(node);
					
					treeEvent.fire('draw',treeroot, node, data[i][property.childName], property, currentLevel);
					
					treeEvent.fire('bindevent',treeroot, node, data[i], property);
					
					treeEvent.fire('render',treeroot, node, data[i], property);
				}
				
				root.appendChild(nodeContainer);
		} else {
			
			if(treeroot === root) {
				var addNew = '<a class="sdui-btn sdui-btn-add sdui-btn-hover-warm sdTree-addNew" href="javascript:void(0)">'+property.defaultBtnText+'</a>';
				
				$(treeroot).append(addNew);
				
				$('.sdTree-addNew').click(function(){
					
					if(property.fields && property.fields.length) {
						var newNode = { };
						
						for(var i=0; i<property.fields.length; i++) {
							newNode[property.fields[i].name]='';
						}
						
						newNode.nodeedit = true;
						
						$(treeroot).find('li[currentlevel]').data('ndata',newNode);
						// $(me).data('ndata',data);
						
						treeEvent.fire('draw',treeroot, root,[newNode],property);
						
					}
					
				});
			}
		}
		
		
		
		
		property.isEditable && treeEvent.fire('toolbar-create',treeroot,property);
		property.isEditable && $(treeroot).find('div.tree-item').addClass('tree-item-edit');
	});
	
	treeEvent.on('render',function(treeroot, node, data,property){
		var icon=$(node).children("div.tree-item").children("div.tree-icon");
		
		property && property.style.icon && property.style.icon.show && icon.addClass("tree-icon-default");
		
		$(node).parent('ul').attr('show')=='true' && $(node).parent('ul').show() || !$(node).parent('ul').hasClass('sdTree') && $(node).parent('ul').attr('show')!='true' && property.isExpandable && $(node).parent('ul').hide();
		
		!property.isExpandable && (data.treeopen=true);
		
		!$(node).parent('ul').hasClass('sdTree') && data.treeopen && $(node).parent('ul').attr('show',true) && $(node).parent('ul').show();
		
		node && sdutil.isArray(data[property.childName]) && data[property.childName].length>0 && icon.removeClass('open').addClass('close') && property.style.icon && property.style.icon.show && icon.removeClass("tree-icon-open").addClass("tree-icon-close");
		
		data.treeopen && sdutil.isArray(data[property.childName]) && data[property.childName].length>0 && $(node).children('ul').show() && icon.removeClass('close').addClass('open') && property.style.icon && property.style.icon.show && icon.removeClass("tree-icon-close").addClass("tree-icon-open");
		
		
	});
	
	treeEvent.on('toolbar-show',function(opts) {
		
		$('.sdTree-toolbar').show();
		
		$('.sdTree-toolbar').css({
			position:'absolute', 
			// left: opts.left? (opts.left-$('.sdTree-toolbar').width()/2) :$(opts.item).offset().left,
			// left: $('.sdTree').width()-150,
			right: 10,
			"z-index": new Date().getTime(),
			top: $(opts.item).position().top 
		});
		
		opts.item && (ctitem=opts.item);
		
		$('.sdTree-toolbar').attr('id',new Date().getTime());
		
		
	});
	
	treeEvent.on('toolbar-hide',function() {
		
		var time,toolid = $('.sdTree-toolbar').attr('id');
		
		$('.sdTree-toolbar').hover(function(){
			clearTimeout(time);
		},function(){
			$('.sdTree-toolbar').hide();
		});
		
		time = sdutil.defer(function(){
			if(toolid == $('.sdTree-toolbar').attr('id')) {
				$('.sdTree-toolbar').hide();
			}
		},500);
		
		if(!toolid) {
			$('.sdTree-toolbar').hide();
		}
		
	});
	
	treeEvent.on('complete',function(treeroot,property) {
		
		var data = extract($(treeroot).children('.sdTree').children('li'));
		
		treeEvent.fire('draw',treeroot,treeroot,data,property);
		
		if(sdutil.isFunction(property.complete)){
			property.complete.call(this,data,$(treeroot).find('input').size()?true:false);
		} 
		
		function extract(linode) {
			var datas = [],treeopen=false;
			
			linode.each(function(){
				
				var obj = $(this).data('ndata'),fields,cnodes;
				
				if(obj && !obj.nodedel) {
				
					treeopen = $(this).children('.tree-item').children('.tree-icon').hasClass('open');
					
					if(sdutil.isArray(fields=property.fields)) {
						for(var i=0;i<fields.length;i++) {
							if(obj.nodeadd) {
								treeopen=true;
								break;
							}
						}
					}
					
					cnodes = extract($(this).children('ul').children('li'));
					
					if(obj[property.childName] && cnodes.length==0) {
						delete obj[property.childName];
					}else if(cnodes.length){
						obj[property.childName] = cnodes;
					}
					
					for(var i=0;i<cnodes.length;i++){
						cnodes[i].treeopen && (treeopen = true);
					}
					
					obj.treeopen = treeopen;
					
					datas.push(obj);
				}
			});
			
			return datas;
		}
		
	});
	
	treeEvent.on('toolbar-create',function(treeroot,property) {
		
		$('.sdTree-toolbar').remove();
		
		var toolbar = document.createElement('ul');
		toolbar.className = 'sdTree-toolbar';
		toolbar.appendChild(createTool('edit','编辑'));
		toolbar.appendChild(createTool('save','保存'));
		toolbar.appendChild(createTool('delete','删除'));
		toolbar.appendChild(createTool('insertAfter','添加结点（在下方）'));
		toolbar.appendChild(createTool('insertBefore','添加结点（在上方）'));
		toolbar.appendChild(createTool('insertChild','添加子节点'));
		treeroot.appendChild(toolbar);
		
		$('.sdTree-toolbar .edit').click(function(){
			var fields,me = ctitem;;
			
			checkinput(function(){
				storeInput();
				var nodedata = $(me).parents('li').eq(0).data('ndata');
				nodedata.nodeedit = true;
				
				treeEvent.fire('complete',treeroot,property);
			});
			
			$(treeroot).find('input').click(function(e){
				e.stopPropagation();
			});
		});
		
		$('.sdTree-toolbar .save').click(function(){
			var me = ctitem;
			saveinput($(me).parent('li'),property);
		});
		
		$('.sdTree-toolbar .delete').click(function(){
			
			var me = ctitem;
			
			confirm('确定删除吗？',function(){
				var nodedata = $(me).parents('li').eq(0).data('ndata');
				nodedata.nodedel = true;
				
				storeInput();
				
				treeEvent.fire('complete',treeroot,property);
				
				message('删除成功');
			});
			
		});
		
		$('.sdTree-toolbar .insertAfter').click(function(){
			checkinput(function(){
				var me = ctitem;
				createItem($(me).parent('li'),2);
			});
		});
		
		$('.sdTree-toolbar .insertBefore').click(function(){
			checkinput(function(){
				var me = ctitem;
				createItem($(me).parent('li'),1);
			});
		});
		
		$('.sdTree-toolbar .insertChild').click(function(){
			checkinput(function(){
				
				var me = ctitem;
				!$(me).next('ul').size() && $('<ul class="ul-bg"></ul>').insertAfter($(me)) && createItem($(me).next('ul')) || createItem($(me).next('ul'));
			});
		});
		
		function createTool(className,title) {
			var tool = document.createElement('li');
			tool.className = className;
			tool.title = title;
			return tool;
		}
		
		function storeInput(){
			// 绑定当前编辑中的input框值
			$(treeroot).find('input').each(function(){
				var currentinput = $(this).parents('li').eq(0).data('ndata');
				currentinput[$(this).attr('name')] = $(this).val();
				
			});
		}
		
		function createItem(ctitem,type) {
			var me = ctitem,fields,obj={},item,time=new Date().getTime();
			storeInput();
			if(sdutil.isArray(fields=property.fields)) {
				for(var i=0;i<fields.length;i++) {
					obj[fields[i].name] = '';
				}
				obj.nodeadd=true;
			}
			
			item = document.createElement('li');
			
			$(item).data('ndata',obj);
			
			switch(type) {
				case 1:
					$(me).before(item);
					break;
				case 2:
					$(me).after(item);
					break;
				case 3:
				default:
					$(me).append(item);
			}
			
			treeEvent.fire('complete',treeroot,property);
			
			$(treeroot).find('input').click(function(e){
				e.stopPropagation();
			});
			
			return true;
		}
		
		function saveinput(ctitem,property) {
			var me=ctitem,currentnodeData=$(me).data('ndata'),pNode,pass=true;
			storeInput();
			
			$(me).find('input').each(function(){
				
				if($(this).attr('mandantory')=='true' && sdutil.trim($(this).val())=='') {
					$(this).addClass('error');
					
					pass=false;
					
				} else {
					$(this).removeClass('error');
					
					currentnodeData[$(this).attr('name')] = $(this).val();
				}
				
			});
			
			pNode = $(me).parents('li')[0];
			
			// 保存结点数据
			pass && sdutil.isFunction(property.saveNode) && property.saveNode.call(this,currentnodeData,$(pNode).data('ndata'),function(data){
				
				data.nodeadd=false;
				data.nodeedit=false;
				
				$(me).data('ndata',data);
				
				treeEvent.fire('complete',treeroot,property);
				treeEvent.fire('toolbar-status-normal',property,$(pNode).attr('currentLevel'));
			});
			
			if(!sdutil.isFunction(property.saveNode) && pass) {
				currentnodeData.nodeadd=false;
				currentnodeData.nodeedit=false;
				treeEvent.fire('complete',treeroot,property);
				treeEvent.fire('toolbar-status-normal',property,$(pNode).attr('currentLevel'));
			}
		}
		
		function checkinput(callback) {
			//$(treeroot).find('input').size() && alert('请先保存编辑项！') || !$(treeroot).find('input').size() && sdutil.isFunction(callback) && callback.call(this);
			sdutil.isFunction(callback) && callback.call(this);
		}
		
		
		treeEvent.fire('toolbar-hide');
		
	});
	
	treeEvent.on('toolbar-status-edit',function(treeroot,property) {
		$('.sdTree-toolbar .edit').hide();
		$('.sdTree-toolbar .save').show();
		$('.sdTree-toolbar .delete').show();
		$('.sdTree-toolbar .insertAfter').show();
		$('.sdTree-toolbar .insertBefore').show();
		$('.sdTree-toolbar .insertChild').hide();
	});
	
	treeEvent.on('toolbar-status-normal',function(property,clevel) {
		$('.sdTree-toolbar .edit').show();
		$('.sdTree-toolbar .save').hide();
		$('.sdTree-toolbar .delete').show();
		$('.sdTree-toolbar .insertAfter').show();
		$('.sdTree-toolbar .insertBefore').show();
		
		if(property.level && clevel && clevel >= property.level) {
			$('.sdTree-toolbar .insertChild').hide();
		} else {
			$('.sdTree-toolbar .insertChild').show();
		}
		
	});
	
	treeEvent.on('bindevent',function(treeroot, linode, nodedata, property) {
		
		property.isExpandable && $(linode).children("div.tree-item").children("div.tree-icon").click(function(e){
			
			if($(this).parent().siblings('ul').size()>0) {
				if($(this).parent().siblings('ul:visible').size()>0) {
					$(this).parent().siblings('ul').hide();
					property && property.style.icon && property.style.icon.show && $(this).parent().children("div.tree-icon").removeClass('open').addClass('close').removeClass("tree-icon-open").addClass("tree-icon-close");
				} else {
					$(this).parent().siblings('ul').show();
					property && property.style.icon && property.style.icon.show && $(this).parent().children("div.tree-icon").removeClass('close').addClass('open').removeClass("tree-icon-close").addClass("tree-icon-open");
				}
			}
			e.stopPropagation();
		});
		
		var cleartime;
		// 浮动toolbar
		$(linode).children("div.tree-item").hover(function(e){
			var me = this;
			
			property.toolbar!=1 && (cleartime = sdutil.defer(function(){
				
				$(me).find('input').size()&& treeEvent.fire('toolbar-status-edit') || !$(me).find('input').size() && treeEvent.fire('toolbar-status-normal',property,$(me).attr('currentLevel'));
				
				treeEvent.fire('toolbar-show',{item:$(me)[0]});
			},100,true));
			
		},function(){
			clearTimeout(cleartime);
			treeEvent.fire('toolbar-hide');
		});
		
		$(linode).data('ndata',nodedata);
		
		property.style.hover.show==true && $(linode).find("div.tree-check").hover(function(){
			$(this).parents('.tree-title').addClass('tree-title-hover');
			
		},function(){
			$(this).parents('.tree-title').removeClass('tree-title-hover');
		});
		
		if(property.style.hover.pointer==true) {
			if(property.isNodeClickable) {
				
				$(linode).children("div.tree-item").hover(function(){
					$(this).addClass('tree-title-hover-pointer');
					
				},function(){
					$(this).removeClass('tree-title-hover-pointer');
				});
				
			}else{
				$(linode).children("div.tree-item").find("div.tree-check").hover(function(){
					$(this).addClass('tree-title-hover-pointer');
					
				},function(){
					$(this).removeClass('tree-title-hover-pointer');
				});
			}
		}
		
		$(linode).children("div.tree-item").find("div.tree-check").hover(function(){
			$(this).addClass('tree-title-hover-pointer');
			
		},function(){
			$(this).removeClass('tree-title-hover-pointer');
		});
		
		function checkTreeNode(data,property,flag){
			if(data) {
				
				if(property.style.select && property.style.select.multiple==true) {
					var chilrenNodes = data[property.childName];
					
					if(sdutil.isArray(chilrenNodes)) {
						
						for(var i=0;i<chilrenNodes.length;i++){
							checkTreeNode(chilrenNodes[i],property, flag);
						}
					}
					
					data.checked = flag;
					
				} else {
					checkNode($(treeroot).children('.sdTree').children('li'),false);
					
					data.checked = true;
				}
			}
		}
		
		function checkNode(linode,flag) {
			
			linode.each(function(){
				
				var obj = $(this).data('ndata');
				
				if(obj) {
					obj.checked = flag;
					checkNode($(this).children('ul').children('li'));
				}
			});
		}
		
		function populateSelectedNodes(linode,property) {
			
			linode.each(function(){
				
				var obj = $(this).data('ndata');
				
				if(obj) {
					
					if($(this).children('ul').children('li').size()){
						populateSelectedNodes($(this).children('ul').children('li'),property);
					}
					
					if($(this).children('ul').children('li').size()==0 && obj.checked){
						property.selectedNodes.push(obj);
					}
				}
			});
		}
		
		
		if(property.isNodeClickable) {
			$(linode).find("div.tree-item").unbind('click');
			$(linode).find("div.tree-item").click(function(e){
				var nodedata = $(this).parents('li').data('ndata');
				
				if(nodedata && nodedata.checked) {
					checkTreeNode(nodedata,property,false);
				} else {
					checkTreeNode(nodedata,property,true);
				}
				
				
				treeEvent.fire('complete',treeroot,property);
				
				property.selectedNodes = [];
				populateSelectedNodes($(treeroot).children('.sdTree').children('li'),property)
				
				property.nodeClick.call('',nodedata,property.selectedNodes);
				
				e.stopPropagation();
			});
		}else{
			$(linode).find("div.tree-check").unbind('click');
			$(linode).find("div.tree-check").click(function(e){
				var nodedata = $(this).parents('li').data('ndata');
				
				if(nodedata && nodedata.checked) {
					checkTreeNode(nodedata,property,false);
				} else {
					checkTreeNode(nodedata,property,true);
				}
				
				
				treeEvent.fire('complete',treeroot,property);
				
				property.selectedNodes = [];
				populateSelectedNodes($(treeroot).children('.sdTree').children('li'),property)
				
				property.nodeClick.call('',nodedata,property.selectedNodes);
				
				e.stopPropagation();
			});
		}
		
		
		
		
	});
	
	$.fn.sdTree = function(opts,nodes) {
			
			var _this = this;
			
			
			// tree默认属性
			var defaults = {
				isEditable:false
				,isNodeClickable:false   //结点内容是否可点击
				,defaultBtnText:'添加'
				,level:null // 结点层数， 此参数只在编辑情况下有效
				,style:{
					icon:{ // icon样式 TODO，后期可增强图标显示控制
						show:true  // true：显示树型结构图标，false：不显示图标
					}
					,select:{ // 选中效果控制，TODO，后期可增强选中效果
						show:true // 是否显示选中效果
						,multiple:false
					}
					,hover:{ //滑过效果 TODO，后期可增强滑过效果
						pointer:true // 滑过是否显示手型
						,show:true // 是否显示滑过效果
					}
				}
				,isExpandable:true
				,toolbar:1  // 1：跟随鼠标移动，其他值：固定在结点的左上角
				,fields:[{name:'title',width:'',length:''},{name:'date',width:'',length:''}]
				,childName: 'childNodes'
				,nodeInnerHtml:function(data) {
					// 覆写此方法，用于渲染tree的显示
					
					// return的内容作为页面显示
					return '';
				}
				,nodeClick:function(data,selectedNodes) {
					// 覆写此方法，用于渲染tree结点的点击回调，使用者可以直接操作此条记录
				}
				,complete: function(datas) {
					// tree上的全部数据集
					
				}
				,saveNode:null // saveNode是一个回调函数，格式：参数node表示当前结点数据，callback是成功后的回调函数，function(node,callback) { callback.call(this,{name:'xxx'}); }
			};
			
			defaults.selectedNodes = [];
			
			var property = $.extend(true,{},defaults, opts);
			
			
			if(sdutil.isArray(nodes)) { 
				treeEvent.fire('draw',_this[0], _this[0],nodes,property);
			} else {
				
				var ajaxDefaults = {
						url: '',
						type:'GET',
						async:true,	// 是否异步 
						data:{},  	// 待发送数据
						waitFlag:true, //是否显示等待动画
						lazy:false, // true：表示懒加载，点击展开时，才会加载， false：表示初始化时后续数据已经加载完毕了 TODO
				};
				
				property = $.extend(true,{},ajaxDefaults, property);
				
				treeEvent.fire('load',_this[0],property);
			}
		}
	
})(jQuery);


/**
 * 自动完成插件，方便项目完成自动联想下拉框
 * @param $
 * 
 * @author wangbinbin
 * @date 2016/12/02
 */
(function($){
	
	var ac = new SdEvent();
	ac.on('load',function(selector,property){
		if(property.type=='post' || property.type == 'POST') {
			property.contentType="application/json";
		}
		
		$.sdAjax({
			url: property.url,
			cache: property.cache,
			dataType: 'json',
			type: property.type,
			data: property.data,
			contentType: property.contentType,
			async: property.async,
			waitFlag:property.waitFlag,
			successCallback: function(data){
				if(data && data.data) {
					ac.fire('draw',selector,data.data,property);
				}
			}
		});
	});
	
	ac.on('draw',function(selector,datas,property){
		
		$(selector).next('ul.sdAutoComplete').remove();
		
		var selectItemContainer = document.createElement('ul');
		selectItemContainer.className='sdAutoComplete';
		selectItemContainer.style.listStyle = 'none';
		selectItemContainer.style.margin = '0px';
		selectItemContainer.style.padding = '0px';
		selectItemContainer.style.maxHeight = '200px';
		selectItemContainer.style.overflowY = 'auto';
		selectItemContainer.style.overflowX = 'hidden';
		selectItemContainer.style.textAlign = 'left';
		
		selectItemContainer.style.width = $(selector).width()+'px';
		selectItemContainer.style.background = '#fff';
		
		selectItemContainer.style.border = '1px solid #e6e6e6';
		if($(selector).css('border')) {
			selectItemContainer.style.border = $(selector).css('border');
		}
		selectItemContainer.style.zIndex = new Date().getTime();
		selectItemContainer.style.position = 'absolute';
		
		selectItemContainer.style.display = 'none'; // 下拉框默认不显示
		
		if(sdutil.isArray(datas)) {
			for(var i=0;i<datas.length;i++) {
				var selectItem = document.createElement('li');
				selectItem.style.padding = '0px 5px';
				selectItem.style.width = selectItemContainer.style.width;
				selectItem.style.display = 'block';
				selectItem.style.height = '2em';
				selectItem.style.lineHeight = '2em';
				selectItem.style.whiteSpace = 'nowrap';
				selectItem.style.overflow = 'hidden';
				selectItem.style.textAlign = 'left';
				selectItem.style.textOverflow = 'ellipsis';
				selectItem.innerHTML = property.innerHtml.call('',datas[i]);
				$(selectItem).data('data',datas[i]);
				selectItemContainer.appendChild(selectItem);
			}
		}
		$(selectItemContainer).insertAfter($(selector));
		
		ac.fire('render',selector,property);
	});
	
	ac.on('render',function(selector,property){
		$(selector).next('ul.sdAutoComplete').find('li').hover(function(){$(this).addClass('item-hover');},function(){$(this).removeClass('item-hover');});
		
		$(selector).next('ul.sdAutoComplete').find('li').click(function(){
			$(selector).val($(this).text());
			sdutil.isFunction(property.itemClick) && property.itemClick.call(this,$(this).data('data'));
		});
		
		$(document).click(function(){$('ul.sdAutoComplete').hide();});
		
		$(selector).keyup(function(){
			var ddlist = $(selector).next('ul.sdAutoComplete');
			ddlist.show();
			ddlist.children('li').hide();
			$(selector).val() && ddlist.children('li:contains("'+$(selector).val()+'")').size()>0 && ddlist.children('li:contains("'+$(selector).val()+'")').show() || $(selector).val() && ddlist.children('li:contains("'+$(selector).val()+'")').size()==0&&ddlist.hide();
		});
		
		setInterval(function(){
			$(selector).next('ul.sdAutoComplete').css({ left:$(selector).offset().left,top:$(selector).offset().top+$(selector).height()+2})
		},50);
	});
	
	
	// 自动完成插件
	$.fn.sdAutoComplete = function(opts,datas) {
		
		var me=this;
		
		var defaults = {
			innerHtml:function(data){
				// 这里边可以自定义html代码，具体参考sdGrid或sdTree用法
				return '';
			}
			,itemClick:function(data){
				//点击item获取当前记录
			}
		}
		
		var property = $.extend(true,{},defaults, opts);
		
		if(sdutil.isArray(datas)) { 
			ac.fire('draw',me[0], datas, property);
		} else {
			var ajaxDefaults = {
					url: '',
					type:'GET',
					async:true,	// 是否异步 
					data:{},  	// 待发送数据
					waitFlag:true, //是否显示等待动画
					lazy:false, // true：表示懒加载，点击展开时，才会加载， false：表示初始化时后续数据已经加载完毕了 TODO
			};
			
			property = $.extend(true,{},ajaxDefaults, property);
			
			ac.fire('load',me[0],property);
		}
	} 
})(jQuery);



/**
 * 按钮组插件 可根据当前页面路径，自动选中按钮
 * @param $
 * 
 * @author wangbinbin
 * @date 2016/12/19
 */
(function($){
	
	$.fn.sdGroupButton = function(opts){
		var me=this;
		
		var defaults = {
			items:[] // 待显示按钮组  ，格式 e.g. [{name:'',link:''},{name:'',link:''}......]
		};
		
		var property = $.extend(true,{},defaults, opts);
		
		var toolbar = document.createElement('ul');
		toolbar.className = 'sdGroupButton';
		
		for(var i=0;i<property.items.length;i++) {
			var item = document.createElement('li');
			item.innerHTML=property.items[i].name;
			
			if(window.location.href.indexOf(property.items[i].link)>0) {
				item.className='active';
			}
			
			if(i==(property.items.length-1)) {
				item.style.width = 'auto';
				item.style.float = 'none';
			}else{
				item.style.width = (100/property.items.length)+'%';
			}
			
			item['setAttribute']('link', property.items[i].link);
			
			toolbar.appendChild(item);
		}
		
		me[0].appendChild(toolbar);
		
		$(toolbar).find('li').click(function(){
			window.location.href = $(this).attr('link');
		});
	}
	
})(jQuery);